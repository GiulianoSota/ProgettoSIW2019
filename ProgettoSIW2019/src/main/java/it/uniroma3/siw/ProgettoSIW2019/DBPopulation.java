package it.uniroma3.siw.ProgettoSIW2019;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.ProgettoSIW2019.model.Funzionario;
import it.uniroma3.siw.ProgettoSIW2019.repository.FunzionarioRepository;

@Component
public class DBPopulation implements ApplicationRunner {

    @Autowired
    private FunzionarioRepository funzionarioRepository;


    public void run(ApplicationArguments args) throws Exception {
        this.deleteAll();
        this.populateDB();
    }

    private void deleteAll() {
        System.out.println("Deleting all users from DB...");
        funzionarioRepository.deleteAll();
        System.out.println("Done");
    }

    private void populateDB() throws IOException, InterruptedException {

        System.out.println("Storing users...");

        Funzionario admin = new Funzionario("m.rossi@mail.com", "mariorossi", "mrpass");
        admin = this.funzionarioRepository.save(admin);

        System.out.println("Done.\n");
    }
}
