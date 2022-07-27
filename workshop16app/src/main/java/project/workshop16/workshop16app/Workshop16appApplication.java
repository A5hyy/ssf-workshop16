package project.workshop16.workshop16app;

/*
HOSTED ON
https://git.heroku.com/radiant-lake-35506.git
heroku login
heroku create (Then copy the heroku.git website url)
git init
git remote add heroku  <replace wif your heroku git url>
git add .
git commit -m "new"
git push heroku master 

TO START PROGRAM ON VS CODE
mvn compile
mvn package
mvn spring-boot:run
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Workshop16appApplication {

	public static void main(String[] args) {
		SpringApplication.run(Workshop16appApplication.class, args);
	}

}
