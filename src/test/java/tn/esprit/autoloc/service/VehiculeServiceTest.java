package tn.esprit.autoloc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.InOrder;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VehiculeServiceTest {

    @Mock
    private VehiculeRepository repository;

    @Mock
    private VehiculeValidator validator;

    @Mock
    private JournalisationService journalisationService;

    @InjectMocks
    private VehiculeService service;

    @Test
    void delegueValidationJournalisationPuisPersistance() {
        Vehicule vehicule = new Vehicule();
        when(repository.save(vehicule)).thenReturn(vehicule);

        Vehicule resultat = service.ajouter(vehicule);

        assertSame(vehicule, resultat);
        InOrder ordre = inOrder(validator, journalisationService, repository);
        ordre.verify(validator).valider(vehicule);
        ordre.verify(journalisationService).journaliserAjout();
        ordre.verify(repository).save(vehicule);
    }
}
