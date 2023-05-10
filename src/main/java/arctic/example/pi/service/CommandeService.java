    package arctic.example.pi.service;

    import arctic.example.pi.entity.Commande;
    import arctic.example.pi.repository.CommandeRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;

    @Service
    public class CommandeService {


        @Autowired
        private CommandeRepository commandeRepository;

        public List<Commande> getAllCommandes() {
            return commandeRepository.findAll();
        }

        public Optional<Commande> getCommandeById(Long id) {
            return commandeRepository.findById(id);
        }

        public void addCommande(Commande commande) {
            if (commande.getEtat() == null) {
                commande.setEtat("Tunis");
            }
            commandeRepository.save(commande);
        }



        public Commande updateCommande(Long id, Commande commande) {
            Optional<Commande> optionalCommande = commandeRepository.findById(id);
            if (optionalCommande.isPresent()) {
                commande.setIdC(id);
                return commandeRepository.save(commande);
            } else {
                return null;
            }
        }

        public void deleteCommande(Long id) {
            commandeRepository.deleteById(id);
        }

        // Services avancées

        public List<Commande> getCommandesByAdressmail(String adressmail) {
            return commandeRepository.findByAdressmail(adressmail);
        }


        public List<Commande> getCommandesByModePaiment(String modePaiment) {
            return commandeRepository.findByModePaiment(modePaiment);
        }


        public List<Commande> findCommandesByNom(String nom) {
            return commandeRepository.findByNom(nom);
        }

        public List<Commande> findCommandesBySociete(String societe) {
            return commandeRepository.findBySociete(societe);
        }


        public List<Commande> findCommandesByAdressmail(String adressmail) {
            return commandeRepository.findByAdressmail(adressmail);
        }


    }
