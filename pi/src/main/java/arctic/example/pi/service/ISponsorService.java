package arctic.example.pi.service;





import arctic.example.pi.entity.Sponsor;

import java.util.List;

public interface ISponsorService {


    List<Sponsor> retrieveAllSponsors();

    Sponsor addOrUpdateSponsor(Sponsor sponsor);

    void removeSponsor (Sponsor sponsor);

    Sponsor retrieveSponsor (Long numSponsor);
}
