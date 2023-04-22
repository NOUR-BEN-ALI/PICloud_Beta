package arctic.example.pi.service;

import arctic.example.pi.entity.Sponsor;
import arctic.example.pi.repository.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SponsorServiceImpl implements ISponsorService {

    @Autowired
    SponsorRepository sponsorRepo;


    public List<Sponsor> retrieveAllSponsors() {
        return (List<Sponsor>) sponsorRepo.findAll();
    }

    public Sponsor addOrUpdateSponsor(Sponsor sponsor) {
        return sponsorRepo.save(sponsor);
    }

    public void removeSponsor(Sponsor sponsor) {
        sponsorRepo.delete(sponsor);
    }

    public Sponsor retrieveSponsor(Long numSponsor) {
        return sponsorRepo.findById(numSponsor).get();
    }
}
