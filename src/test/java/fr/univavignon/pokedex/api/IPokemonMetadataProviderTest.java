package fr.univavignon.pokedex.api;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IPokemonMetadataProviderTest {
    private IPokemonMetadataProvider iPokemonMetadataProvider;
    @BeforeAll
    public void setUp() {
        //création d'un mock pour l'interface IPokemonMetadataProvider
        this.iPokemonMetadataProvider = mock(IPokemonMetadataProvider.class);
    }
    @Test
    public void metaDataShouldBeCorrectlyRetrievedTest() throws PokedexException {
        //récuperer un PockemonMetaData avec un mock sur l'indice 0
        when(this.iPokemonMetadataProvider.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0,
                "Bulbizarre", 126, 126, 90));

        //véfifier que les informations récupérées sont correctes
        PokemonMetadata pokemonMetadata = this.iPokemonMetadataProvider.getPokemonMetadata(0);

        assertEquals("Bulbizarre", pokemonMetadata.getName());
        assertEquals(126, pokemonMetadata.getAttack());
        assertEquals(126, pokemonMetadata.getDefense());
        assertEquals(90, pokemonMetadata.getStamina());
    }
}
