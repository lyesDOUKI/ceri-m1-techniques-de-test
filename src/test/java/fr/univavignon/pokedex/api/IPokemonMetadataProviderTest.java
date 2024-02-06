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
    public void metaDataShouldBeCorrectlyRetrievedBulbizarreTest() throws PokedexException {
        //récuperer un PockemonMetaData avec un mock sur l'indice 0
        when(this.iPokemonMetadataProvider.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0,
                "Bulbizarre", 126, 126, 90));

        //véfifier que les informations récupérées sont correctes
        PokemonMetadata pokemonMetadata = this.iPokemonMetadataProvider.getPokemonMetadata(0);
        //faire les assertions sur les valeurs récupérées
        assertEquals("Bulbizarre", pokemonMetadata.getName());
        assertEquals(126, pokemonMetadata.getAttack());
        assertEquals(126, pokemonMetadata.getDefense());
        assertEquals(90, pokemonMetadata.getStamina());
    }
    @Test
    public void metaDataShouldBeCorrectlyRetrievedAqualiTest() throws PokedexException {
        //récuperer un PockemonMetaData avec un mock sur l'indice 134
        when(this.iPokemonMetadataProvider.getPokemonMetadata(134)).thenReturn(new PokemonMetadata(134,
                "Aquali", 186, 168, 260));

        //véfifier que les informations récupérées sont correctes
        PokemonMetadata pokemonMetadata = this.iPokemonMetadataProvider.getPokemonMetadata(134);
        //faire les assertions sur les valeurs récupérées
        assertEquals("Aquali", pokemonMetadata.getName());
        assertEquals(186, pokemonMetadata.getAttack());
        assertEquals(168, pokemonMetadata.getDefense());
        assertEquals(260, pokemonMetadata.getStamina());
    }
    //test le dépassement de l'index des pokemons, retourner une pokemonException
    @Test
    public void metaDataShouldThrowPokemonExceptionTest() throws PokedexException {
        //récuperer un PockemonMetaData avec un mock sur l'indice 151
        when(this.iPokemonMetadataProvider.getPokemonMetadata(151)).thenThrow(new PokedexException("Pokemon not found"));

        //véfifier que les informations récupérées sont correctes
        assertThrows(PokedexException.class, () -> {
            this.iPokemonMetadataProvider.getPokemonMetadata(151);
        });
    }
    //test la valeur negatif, retourner une pokemonException
    @Test
    public void metaDataShouldThrowPokemonExceptionNegativeIndexTest() throws PokedexException {
        //récuperer un PockemonMetaData avec un mock sur l'indice -1
        when(this.iPokemonMetadataProvider.getPokemonMetadata(-1)).thenThrow(new PokedexException("Pokemon not found"));

        //véfifier que les informations récupérées sont correctes
        assertThrows(PokedexException.class, () -> {
            this.iPokemonMetadataProvider.getPokemonMetadata(-1);
        });
    }
}
