package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IPokemonTrainerFactoryTest {
    private IPokemonTrainerFactory pokemonTrainerFactory;

    @BeforeAll
    public void setUp(){
        pokemonTrainerFactory = mock(IPokemonTrainerFactory.class);
    }
    @Test
    public void PokemTrainerShouldBeCreatedCorrectly(){
        IPokedexFactory pokedexFactory = mock(IPokedexFactory.class);
        Team team = Team.INSTINCT;
        IPokedex pokedex = mock(IPokedex.class);
        IPokemonMetadataProvider pokemonMetadataProvider = mock(IPokemonMetadataProvider.class);
        IPokemonFactory pokemonFactory = mock(IPokemonFactory.class);
        when(pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory))
                .thenReturn(pokedex);
        when(pokemonTrainerFactory.createTrainer("Ash", team, pokedexFactory))
                .thenReturn(new PokemonTrainer("Ash",
                        team, pokedex));
        PokemonTrainer pokemonTrainer = pokemonTrainerFactory.
                createTrainer("Ash", team, pokedexFactory);

        assertNotNull(pokemonTrainer);
        assert(pokemonTrainer.getName().equals("Ash"));
        assert(pokemonTrainer.getTeam().equals(team));
    }
}
