package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IPokedexFactoryTest {

    private IPokedexFactory pokedexFactory;
    private IPokemonMetadataProvider metadataProvider;
    private IPokemonFactory pokemonFactory;
    private RocketPokemonFactory rocketPokemonFactory;

    @BeforeAll
    public void setUp() {
        this.pokedexFactory = new PokedexFactory();
        this.metadataProvider = new PokemonMetadataProviderImpl();
        this.pokemonFactory = new PokemonFactory();
        this.rocketPokemonFactory = new RocketPokemonFactory();
    }

    @Test
    public void testCreatePokedex() {
        IPokedex iPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        assertNotNull(iPokedex);
    }

    @Test
    public void throwExceptionIfParamsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            pokedexFactory.createPokedex(null, null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            pokedexFactory.createPokedex(null, pokemonFactory);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            pokedexFactory.createPokedex(metadataProvider, null);
        });
    }

    @Test
    public void testThatPokedexIsGood() {
        IPokedex iPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        assertNotNull(iPokedex);
        assertEquals(0, iPokedex.size());
    }

    @Test
    public void testCreatePokedexWithNewClass() {
        IPokedex iPokedex = pokedexFactory.createPokedex(metadataProvider, rocketPokemonFactory);
        assertNotNull(iPokedex);
    }

    @Test
    public void throwExceptionIfParamsNullWithNewClass() {
        assertThrows(IllegalArgumentException.class, () -> {
            pokedexFactory.createPokedex(null, null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            pokedexFactory.createPokedex(null, rocketPokemonFactory);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            pokedexFactory.createPokedex(metadataProvider, null);
        });
    }

    @Test
    public void testThatPokedexIsGoodWithNewClass() {
        IPokedex iPokedex = pokedexFactory.createPokedex(metadataProvider, rocketPokemonFactory);
        assertNotNull(iPokedex);
        assertEquals(0, iPokedex.size());
    }
}
