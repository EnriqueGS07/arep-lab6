# TALLER DE TRABAJO INDIVIDUAL EN PATRONES ARQUITECTURALES


### Arquitectura de la aplicación
![image(https://user-images.githubusercontent.com/98104282/224523001-bc4bed36-14c2-40f2-9136-3a0771faf0bd.png)

- El sistema de la figura tiene los siguientes elementos:
- El servicio MongoDB es una instancia de MongoDB corriendo en una máquina virtual de EC2
LogService es un servicio REST que recibe una cadena, la almacena en la base de datos y responde en un objeto JSON con las 10 ultimas cadenas almacenadas en la base de datos y la fecha en que fueron almacenadas.
- La aplicación web APP-LB-RoundRobin está compuesta por un cliente web y al menos un servicio REST. El cliente web tiene un campo y un botón y cada vez que el usuario envía un mensaje, este se lo envía al servicio REST y actualiza la pantalla con la información que este le regresa en formato JSON. El servicio REST recibe la cadena e implementa un algoritmo de balanceo de cargas de Round Robin, delegando el procesamiento del mensaje y el retorno de la respuesta a cada una de las tres instancias del servicio LogService.


### Construción
- Amazon web services: EC2
- Spring-boot y spark
- [Maven](https://maven.apache.org/) - Dependency Management
Se crearon 5 instancias de ec2 en amazon web services y las tres intancias del log services  y al balanceador de cargas se les instalo java, luego se subio el codiogo de java que se ibaa correr y se empezo a correr el servicio
- ![image](https://user-images.githubusercontent.com/98104282/224523652-5d65bdb1-9de3-4d39-bdfa-93de54c5d302.png)
- ![image](https://user-images.githubusercontent.com/98104282/224523658-125e6645-085d-4c3b-b064-f6151bc150ae.png)
- ![image](https://user-images.githubusercontent.com/98104282/224523666-9f255215-e447-4c1e-ab37-f6cc08539b45.png)

- Aqui esta la consola del balanceador de carga imprimiendo a cual servido se le delego la peticion de los logs
- ![image](https://user-images.githubusercontent.com/98104282/224523809-68f47d39-3d53-46d5-8b85-6a4a5c773c04.png)





### Pruebas 
- Despues de desplegar el servicio se realizaron pruebas, Se agregaron varios registros desde ele cliente web y luego se le pidio a la aplicación que mostrara los ultimos 10 logs almacenados
![image](https://user-images.githubusercontent.com/98104282/224523302-aa560732-d9f5-4e60-833e-cc707688ecfa.png)



### Versionamiento
- Version 1.0.0
### Autores
- Enrique González Suárez



