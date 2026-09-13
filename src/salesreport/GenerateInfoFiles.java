package salesreport;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Generates the sample files used as input for the sales report program.
 * Corresponds to Delivery 1 of the project.
 */
public class GenerateInfoFiles {

    private static final Random RANDOM = new Random();

    private static final String[] FIRST_NAMES = {
            "Carlos", "Maria", "Andres", "Laura", "Juan", "Camila",
            "Diego", "Valentina", "Felipe", "Sofia"
    };

    private static final String[] LAST_NAMES = {
            "Gomez", "Rodriguez", "Martinez", "Lopez", "Perez",
            "Sanchez", "Ramirez", "Torres", "Diaz", "Vargas"
    };

    private static final String[] DOCUMENT_TYPES = {"CC", "CE", "TI"};

    /**
     * Generates all sample files: products, salesmen, and one sales file
     * per salesman. Does not request any input from the user.
     */
    public static void main(String[] args) {
        try {
            int productCount = 10;
            int salesmanCount = 5;
            int salesPerSalesman = 8;

            createProductsFile(productCount);
            createSalesManInfoFile(salesmanCount);

            for (int i = 1; i <= salesmanCount; i++) {
                long salesmanId = 1000L + i;
                String salesmanName = "salesman" + i;
                createSalesMenFile(salesPerSalesman, salesmanName, salesmanId);
            }

            System.out.println("Generación de archivos completada exitosamente.");

        } catch (IOException e) {
            System.out.println("Error al generar los archivos: " + e.getMessage());
        }
    }

    /**
     * Creates a sales file for one salesman.
     * Format: DocumentType;DocumentNumber, then one ProductId;Quantity per line.
     */
    public static void createSalesMenFile(int randomSalesCount, String name, long id) throws IOException {
        String fileName = "sales_" + name + ".txt";

        try (FileWriter writer = new FileWriter(fileName)) {
            String documentType = DOCUMENT_TYPES[RANDOM.nextInt(DOCUMENT_TYPES.length)];
            writer.write(documentType + ";" + id + "\n");

            for (int i = 0; i < randomSalesCount; i++) {
                // Product IDs go up to 20 even though fewer products may exist,
                // intentionally producing some inconsistent data for later testing.
                int productId = 1 + RANDOM.nextInt(20);
                int quantitySold = 1 + RANDOM.nextInt(15);
                writer.write(productId + ";" + quantitySold + ";\n");
            }
        }
    }

    /**
     * Creates a file with random products.
     * Format: ProductId;ProductName;UnitPrice
     */
    public static void createProductsFile(int productsCount) throws IOException {
        String fileName = "products.txt";

        try (FileWriter writer = new FileWriter(fileName)) {
            for (int i = 1; i <= productsCount; i++) {
                String productName = "Product" + i;
                int unitPrice = 1000 + RANDOM.nextInt(99) * 500;
                writer.write(i + ";" + productName + ";" + unitPrice + "\n");
            }
        }
    }

    /**
     * Creates a file with random salesmen.
     * Format: DocumentType;DocumentNumber;FirstName;LastName
     */
    public static void createSalesManInfoFile(int salesmanCount) throws IOException {
        String fileName = "salesmen.txt";

        try (FileWriter writer = new FileWriter(fileName)) {
            for (int i = 1; i <= salesmanCount; i++) {
                String documentType = DOCUMENT_TYPES[RANDOM.nextInt(DOCUMENT_TYPES.length)];
                long documentNumber = 1000L + i;
                String firstName = FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)];
                String lastName = LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)];

                writer.write(documentType + ";" + documentNumber + ";" + firstName + ";" + lastName + "\n");
            }
        }
    }
}