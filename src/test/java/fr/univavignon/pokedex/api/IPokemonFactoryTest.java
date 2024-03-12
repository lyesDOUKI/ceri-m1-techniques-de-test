package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IPokemonFactoryTest {

    private IPokemonFactory iPokemonFactory;

    @BeforeAll
    public void setUp() {
        // Création d'un mock pour PokemonMetadataProvider
        this.iPokemonFactory = new PokemonFactory();
    }

    // Test la création d'un pokemon avec les bonnes valeurs
    @Test
    public void createPokemonShouldReturnCorrectPokemonTest() throws PokedexException {
        // Utilisation du mock dans la création du Pokemon
        Pokemon pokemon = this.iPokemonFactory.createPokemon(0, 613, 64,
                4000, 4);

        // Vérification des valeurs récupérées
        assertEquals(0, pokemon.getIndex());
        assertEquals(613, pokemon.getCp());
        assertEquals(64, pokemon.getHp());
        assertEquals(4000, pokemon.getDust());
        assertEquals(4, pokemon.getCandy());

    }
}
