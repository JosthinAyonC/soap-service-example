1. Tener un archivo XSD o un esquema válido o listo para cargarlo dentro del proyecto.
2. Tener la herramienta que descompila el archivo xsd o por su bien mapear a mano todo el wsd.
    1. Nota: La clases generadas no deberían manipularse.
3. Una vez tengamos esto, también debemos mapear las acciones que define el equema (en caso de hacerlo con algún pluggin se omite este paso)
4. Configurar el servidor para que se aliste como un soap service.
    1. [Documentación en springboot](https://spring.io/guides/gs/producing-web-service)
5. Una vez la configuración del WS esté completada, debemos ejecutar el proyecto y acceder al contrato wsdl (el cual se basa cumpliendo las definiciones de un esquema)
    1. Un contrato wsdl se puede ver asi 
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/59b007ff-8d28-4d86-a3ad-d871ff8c14fc/ab2163c1-df52-4a4c-b961-cbc81e100f61/Untitled.png)
    
6. Una vez tengamos esto, debemos crear las acciones o comportamientos que se definen en el archivo de esquema
    1. Ejemplo de un [archivo](https://github.com/JosthinAyonC/soap-service-example/blob/main/src/main/java/sasf/jayon/soapservice/endpoint/ProductsEndpoint.java) que detalla comportamientos
    2. Restricciones que debe cumplir en esta clase:
        1. El comportamiento debe recibir los datos de entrada especificados en el esquema obligatoriamente.
        2. El comportamiento debe retornar los datos de salida especificados en el esquema obligatoriamente.
        3. Si se desea manipular la información es recomendable crear clases y usar un mapper, pero no puedes retornar o recibir algo distinto de la clase que genera el archivo de esquema. 
7. Una vez definimos el comportamiento, podemos pasar a consumir el servicio soap a través de un cliente como [SOAP UI](https://www.soapui.org/downloads/soapui/)
    1. Una vez inicializado el cliente, debemos seleccionar la parte donde dice SOAP y proporcionar el link del servicio donde se sitúa el contrato
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/59b007ff-8d28-4d86-a3ad-d871ff8c14fc/2858575b-703f-44f5-995d-059b3bc14c1d/Untitled.png)
        
    
    b. Después de esto aparecerán todos los comportamientos en la barra de navegación y si abrimos alguno, podremos observar como aparece la estructura del mensaje lista para ser enviada:
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/59b007ff-8d28-4d86-a3ad-d871ff8c14fc/8f7d3d9e-4ebf-4026-9dcb-77ea9e331570/Untitled.png)
    
    c. Si enviamos la petición cumpliendo con el esquema correcto, se procederá  a hacer el proceso de la forma esperada y obtendremos una respuesta como esta especificado en el esquema (en este caso el post, devolvería el objeto en base que fue guardado)
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/59b007ff-8d28-4d86-a3ad-d871ff8c14fc/1b5188b6-21ca-4b0b-a0fb-16b72a7e7718/Untitled.png)
    
    Caso contrario, la petición no llegará a web service debido a que el contrato lo rechazará antes de que entre
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/59b007ff-8d28-4d86-a3ad-d871ff8c14fc/a9859fc9-0be6-4897-854c-4e506fc3a0c8/Untitled.png)