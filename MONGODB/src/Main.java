import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.FindIterable;
import  com.mongodb.client.result.UpdateResult;
import com.mongodb.client.result.DeleteResult;
public class Main {
    public static void main(String[] args) {

        // CONEXION AL MONGO BD
        // INSERTAR

        try(MongoClient mongoClient= MongoClients.create("mongodb://localhost:27017/FOX")) {
            MongoDatabase database = mongoClient.getDatabase("mydb");
            MongoCollection<Document> collection = database.getCollection("mIcollection");
            Document document = new Document("nombre", "walter")
                    .append("edad", 20)
                    .append("apellido", "Cobacango");
            collection.insertOne(document);
            System.out.println("Documento agregado con exito");


        }

        // LEER
        try (MongoClient mongoClient= MongoClients.create("mongodb://localhost:27017/FOX")) {

            MongoDatabase database = mongoClient.getDatabase("mydb");
            MongoCollection<Document> collection = database.getCollection("mIcollection");
            Document filtro = new Document("nombre", "walter");
            FindIterable<Document> documentos = collection.find(filtro);
            for (Document documento : documentos) {
                //System.out.println(documento.toJson());
                String nombre = documento.getString("nombre");
                String apellido = documento.getString("apellido");
                int edad = documento.getInteger("edad");

                System.out.println("Nombre: " + nombre);
                System.out.println("Apellido: " + apellido);
                System.out.println("Edad: " + edad);
            }
        }

        // ACTUALIZAR
        try (MongoClient mongoClient= MongoClients.create("mongodb://localhost:27017/FOX")) {
            MongoDatabase database = mongoClient.getDatabase("mydb");
            MongoCollection<Document> collection = database.getCollection("mIcollection");
            Document filtro = new Document("nombre", "walter");
            Document actualizacion = new Document("$set", new Document("apellido", "Gómez"));
            UpdateResult resultado = collection.updateOne(filtro, actualizacion);
            System.out.println("Documentos modificados: " + resultado.getModifiedCount());
        }

        //borrar
        try (MongoClient mongoClient= MongoClients.create("mongodb://localhost:27017/FOX")) {

            MongoDatabase database = mongoClient.getDatabase("miBaseDeDatos");
            MongoCollection<Document> collection = database.getCollection("miColeccion");
            Document filtro = new Document("nombre", "Juan");
            DeleteResult resultado = collection.deleteOne(filtro);
            System.out.println("Documentos borrados: " + resultado.getDeletedCount());
        }

    }
}