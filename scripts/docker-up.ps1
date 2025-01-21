# Navigate to the project directory
cd "C:/Users/youdhbir/Learning/MNext/product-service"

# Remove application image
# docker-compose down --rmi all

# Clean the Maven project
mvn clean package

# Run Docker Compose with the build option
docker-compose up --build