package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IPokedexTest {
    private IPokedex iPokedex;

    @BeforeAll
    public void setUp() {
        this.iPokedex = new PokedexImplement();
        this.iPokedex.addPokemon(new Pokemon(0,
                "Bulbizarre", 130, 130,
                100, 613, 64,
                4000, 4, 0.91));
        this.iPokedex.addPokemon(new Pokemon(1,
                "Herbizarre", 160, 158,
                120, 618, 64,
                4000, 4, 0.91));
        this.iPokedex.addPokemon(new Pokemon(2,
                "Florizarre", 198, 200,
                160, 613, 64,
                4000, 4, 0.91));
    }

    @Test
    public void getAGoodSize() {
        //assert que la taille est entre 0 et 151
        assertTrue(this.iPokedex.size() >= 0 && this.iPokedex.size() <= 151);
    }

    @Test
    void addPokemon() throws PokedexException {
        Pokemon pokemon = new Pokemon(3,
                "Salamèche", 128, 108,
                78, 613, 64,
                4000, 4, 0.91);
        int index = this.iPokedex.addPokemon(pokemon);
        Pokemon pokemonAdded = this.iPokedex.getPokemon(index);
        assertEquals("Salamèche", pokemonAdded.getName());
    }

    @Test
    void getPokemon() throws PokedexException {
        Pokemon pokemon = this.iPokedex.getPokemon(0);
        assertEquals("Bulbizarre", pokemon.getName());
        assertEquals(Pokemon.class, pokemon.getClass());
    }

    @Test
    public void testGetPokemonThrowsPokedexException() {
        assertThrows(PokedexException.class, () -> {
            this.iPokedex.getPokemon(151);
        });
    }

    @Test
    void getPokemons() {
        List<Pokemon> pokemons = this.iPokedex.getPokemons();
        assertNotNull(pokemons);
    }

    @Test
    void testGetPokemonsFiltredByName() {
        List<Pokemon> pokemons = this.iPokedex.getPokemons(PokemonComparators.NAME);
        assertNotNull(pokemons);
        assertEquals("Bulbizarre", pokemons.get(0).getName());
    }

    @Test
    public void getPokemonsFiltredByIndex() {
        List<Pokemon> pokemons = this.iPokedex.getPokemons(PokemonComparators.INDEX);
        assertNotNull(pokemons);
        assertEquals("Florizarre", pokemons.get(pokemons.size() - 1).getName());
    }

    @Test
    public void getPokemonsFiltredByCP() {
        List<Pokemon> pokemons = this.iPokedex.getPokemons(PokemonComparators.CP);
        assertNotNull(pokemons);
        assertEquals("Herbizarre", pokemons.get(pokemons.size() - 1 ).getName());
    }
}
