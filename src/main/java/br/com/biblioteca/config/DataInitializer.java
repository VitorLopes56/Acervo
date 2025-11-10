package br.com.biblioteca.config;

// Este arquivo foi esvaziado para desativar o DataInitializer, que não é mais necessário.
// O arquivo pode ser removido manualmente.
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {

    @Override
    public void run(org.springframework.boot.ApplicationArguments args) throws Exception {
        // A inicialização de dados agora é gerenciada exclusivamente pelo Liquibase.
        // Este componente foi desativado para evitar conflitos e redundância.
    }
}
