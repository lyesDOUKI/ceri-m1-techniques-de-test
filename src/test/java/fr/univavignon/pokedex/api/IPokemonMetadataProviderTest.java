package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IPokemonMetadataProviderTest {
    private IPokemonMetadataProvider iPokemonMetadataProvider;

    @BeforeAll
    public void setUp() {
        // Création d'une instance réelle de PokemonMetadataProviderImpl
        this.iPokemonMetadataProvider = new PokemonMetadataProviderImpl();
    }

    @Test
    public void metaDataShouldBeCorrectlyRetrievedBulbizarreTest() throws PokedexException {
        // Vérification des informations récupérées pour Bulbizarre
        PokemonMetadata pokemonMetadata = this.iPokemonMetadataProvider.getPokemonMetadata(0);
        assertEquals("Bulbasaur", pokemonMetadata.getName());
        assertEquals(126, pokemonMetadata.getAttack());
        assertEquals(126, pokemonMetadata.getDefense());
        assertEquals(90, pokemonMetadata.getStamina());
    }

    @Test
    public void metaDataShouldBeCorrectlyRetrievedPikachuTest() throws PokedexException {
        PokemonMetadata pokemonMetadata = this.iPokemonMetadataProvider.getPokemonMetadata(25);
        assertEquals("Pikachu", pokemonMetadata.getName());
        assertEquals(112, pokemonMetadata.getAttack());
        assertEquals(101, pokemonMetadata.getDefense());
        assertEquals(70, pokemonMetadata.getStamina());
    }

    @Test
    public void metaDataShouldThrowPokemonExceptionTest() {
        // Vérification du lancement de l'exception pour un index invalide (151)
        assertThrows(PokedexException.class, () -> {
            this.iPokemonMetadataProvider.getPokemonMetadata(151);
        });
    }

    @Test
    public void metaDataShouldThrowPokemonExceptionNegativeIndexTest() {
        // Vérification du lancement de l'exception pour un index négatif (-1)
        assertThrows(PokedexException.class, () -> {
            this.iPokemonMetadataProvider.getPokemonMetadata(-1);
        });
    }
}
