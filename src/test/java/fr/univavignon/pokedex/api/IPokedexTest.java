package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IPokedexTest {
    private IPokedex iPokedex;
    List<Pokemon> pokemons;
    @BeforeAll
    public void setUp() {
        //création d'un mock pour l'interface IPokedex
        this.iPokedex = mock(IPokedex.class);
        //créer une liste de pokemons pour tester les méthodes sur la liste des pokemons
        pokemons = Arrays.asList(
                new Pokemon(0,
                        "Bulbizarre", 130, 130,
                        100, 613, 64,
                        4000, 4, 0.91),
                new Pokemon(1,
                        "Herbizarre", 160, 158,
                        120, 613, 64,
                        4000, 4, 0.91),
                new Pokemon(2,
                        "Florizarre", 198, 200,
                        160, 613, 64,
                        4000, 4, 0.91),
                new Pokemon(133, "Aquali", 186,
                        168, 260, 2729, 202
                        , 5000, 4, 0.91)
        );
    }
    @Test
    public void getAGoodSize() {
        when(this.iPokedex.size()).thenReturn(151);
        assertEquals(151, this.iPokedex.size());
    }


    @Test
    void addPokemon() throws PokedexException {
        //tester ajouter un pokemon
        Pokemon pokemon = new Pokemon(3,
                "Salamèche", 128, 108,
                78, 613, 64,
                4000, 4, 0.91);
        when(this.iPokedex.addPokemon(pokemon)).thenReturn(3);
        //verifier que le pokemon a bien été ajouté, taille de la liste
        int index = this.iPokedex.addPokemon(pokemon);
        //recuperer le pokemon ajouté
        when(this.iPokedex.getPokemon(index)).thenReturn(pokemon);
        Pokemon pokemonAdded = this.iPokedex.getPokemon(index);
        assertEquals("Salamèche", pokemonAdded.getName());

    }

    @Test
    void getPokemon() throws PokedexException {
        //recuperer un pokem pour un index donné
        when(this.iPokedex.getPokemon(0)).thenReturn(new Pokemon(0,
                "Bulbizarre", 130, 130,
                100, 613, 64,
                4000, 4, 0.91));
        Pokemon pokemon = this.iPokedex.getPokemon(0);
        assertEquals("Bulbizarre", pokemon.getName());
        //tester que c'est bien un type Pokemon
        assertEquals(Pokemon.class, pokemon.getClass());
    }
    @Test
    public void testGetPokemonThrowsPokedexException() throws PokedexException {
        //recuperer un pokem pour un index donné
        when(this.iPokedex.getPokemon(151)).thenThrow(new PokedexException("Pokemon not found"));
        //véfifier que les informations récupérées sont correctes
        assertThrows(PokedexException.class, () -> {
            this.iPokedex.getPokemon(151);
        });
    }

    @Test
    void getPokemons() {
        //tester la listes des pockomens
        when(this.iPokedex.getPokemons()).thenReturn(pokemons);
        List<Pokemon> pokemons = this.iPokedex.getPokemons();
        //la liste ne doit pas pouvoir être modifiée, donc on ne peut pas add
        //ou remove des éléments
        assertNotNull(pokemons);
        assertEquals(4, pokemons.size());
        try{
            pokemons.add(new Pokemon(3,
                    "Salamèche", 128, 108,
                    78, 613, 64,
                    4000, 4, 0.91));
            fail("La liste ne doit pas pouvoir être modifiée");
        } catch (UnsupportedOperationException e) {
            //la liste ne doit pas pouvoir être modifiée
        }
        //on revérifie que la liste ne peut pas être modifiée
        assertEquals(4, pokemons.size());
    }

    @Test
    void testGetPokemonsFiltredByName() {
        //tester la listes des pockomons en utilisant un comparator
        //créer une liste de pokemon triée par nom
        pokemons = Arrays.asList(
                new Pokemon(133, "Aquali", 186,
                        168, 260, 2729, 202
                        , 5000, 4, 0.91),
                new Pokemon(0,
                        "Bulbizarre", 130, 130,
                        100, 613, 64,
                        4000, 4, 0.91),
                new Pokemon(1,
                        "Herbizarre", 160, 158,
                        120, 613, 64,
                        4000, 4, 0.91),
                new Pokemon(2,
                        "Florizarre", 198, 200,
                        160, 613, 64,
                        4000, 4, 0.91)
        );
        when(this.iPokedex.getPokemons(PokemonComparators.NAME)).thenReturn(pokemons);
        List<Pokemon> pokemons = this.iPokedex.getPokemons(PokemonComparators.NAME);
        //la liste ne doit pas pouvoir être modifiée, donc on ne peut pas add
        try{
            pokemons.add(new Pokemon(3,
                    "Salamèche", 128, 108,
                    78, 613, 64,
                    4000, 4, 0.91));
            fail("La liste ne doit pas pouvoir être modifiée");
        } catch (UnsupportedOperationException e) {
            //la liste ne doit pas pouvoir être modifiée
        }
        //verifier que aqua est bien le premier de la liste
        assertEquals("Aquali", pokemons.get(0).getName());
    }
    @Test
    public void getPokemonsFiltredByIndex() {
        //tester la listes des pockomons en utilisant un comparator
        //créer une liste de pokemon triée par index

        when(this.iPokedex.getPokemons(PokemonComparators.INDEX)).thenReturn(pokemons);
        List<Pokemon> pokemons = this.iPokedex.getPokemons(PokemonComparators.INDEX);
        //la liste ne doit pas pouvoir être modifiée, donc on ne peut pas add
        try{
            pokemons.add(new Pokemon(3,
                    "Salamèche", 128, 108,
                    78, 613, 64,
                    4000, 4, 0.91));
            fail("La liste ne doit pas pouvoir être modifiée");
        } catch (UnsupportedOperationException e) {
            //la liste ne doit pas pouvoir être modifiée
        }
        assertEquals("Aquali", pokemons.get(pokemons.size() - 1).getName());
    }
    @Test
    public void getPokemonsFiltredByCP() {
        //tester la listes des pockomons en utilisant un comparator
        //créer une liste de pokemon triée par CP
        pokemons = Arrays.asList(
                new Pokemon(0,
                        "Bulbizarre", 130, 130,
                        100, 613, 64,
                        4000, 4, 0.91),
                new Pokemon(1,
                        "Herbizarre", 160, 158,
                        120, 613, 64,
                        4000, 4, 0.91),
                new Pokemon(2,
                        "Florizarre", 198, 200,
                        160, 613, 64,
                        4000, 4, 0.91),
                new Pokemon(133, "Aquali", 186,
                        168, 260, 2729, 202
                        , 5000, 4, 0.91)
        );
        when(this.iPokedex.getPokemons(PokemonComparators.CP)).thenReturn(pokemons);
        List<Pokemon> pokemons = this.iPokedex.getPokemons(PokemonComparators.CP);
        //la liste ne doit pas pouvoir être modifiée, donc on ne peut pas add
        try{
            pokemons.add(new Pokemon(3,
                    "Salamèche", 128, 108,
                    78, 613, 64,
                    4000, 4, 0.91));
            fail("La liste ne doit pas pouvoir être modifiée");
        } catch (UnsupportedOperationException e) {
            //la liste ne doit pas pouvoir être modifiée
        }
        assertEquals("Aquali", pokemons.get(pokemons.size() - 1 ).getName());
    }
    @Test
    void createPokemonTest() throws PokedexException {

        IPokemonFactory pokemonFactory = mock(IPokemonFactory.class);
        when(pokemonFactory.createPokemon(0, 613, 64, 4000, 4)).thenReturn(new Pokemon(0, "Bulbasaur", 126, 126, 90, 613, 64, 4000, 4, 0.91));

        IPokemonMetadataProvider pokemonMetadataProvider = mock(IPokemonMetadataProvider.class);
        when(pokemonMetadataProvider.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Bulbasaur", 126, 126, 90));

        IPokedex pokedex = new PokedexImplement(pokemonFactory, pokemonMetadataProvider);

        Pokemon createdPokemon = pokedex.createPokemon(0, 613, 64, 4000, 4);

        assertEquals("Bulbasaur", createdPokemon.getName());
        assertEquals(126, createdPokemon.getAttack());
        assertEquals(126, createdPokemon.getDefense());
        assertEquals(90, createdPokemon.getStamina());
    }
    @Test
    void getPokemonMetadataTest() throws PokedexException {

        IPokemonMetadataProvider pokemonMetadataProvider = mock(IPokemonMetadataProvider.class);
        IPokemonFactory pokemonFactory = mock(IPokemonFactory.class);
        when(pokemonMetadataProvider.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Bulbasaur", 126, 126, 90));


        IPokedex pokedex = new PokedexImplement(pokemonFactory, pokemonMetadataProvider);


        PokemonMetadata pokemonMetadata = pokedex.getPokemonMetadata(0);


        assertEquals("Bulbasaur", pokemonMetadata.getName());
        assertEquals(126, pokemonMetadata.getAttack());
        assertEquals(126, pokemonMetadata.getDefense());
        assertEquals(90, pokemonMetadata.getStamina());
    }

    @Test
    void testAddPokemon() {
        IPokemonFactory pokemonFactory = mock(IPokemonFactory.class);
        IPokemonMetadataProvider pokemonMetadataProvider = mock(IPokemonMetadataProvider.class);
        IPokedex pokedex = new PokedexImplement(pokemonFactory, pokemonMetadataProvider);

        Pokemon pokemon = new Pokemon(0, "Bulbasaur", 126, 126, 90, 613, 64, 4000, 4, 0.91);

        int index = pokedex.addPokemon(pokemon);
        assertEquals(0, index);


        assertThrows(IllegalArgumentException.class, () -> {
            pokedex.addPokemon(pokemon);
        });
    }

    @Test
    void testAddMultiplePokemon() {
        IPokemonFactory pokemonFactory = mock(IPokemonFactory.class);
        IPokemonMetadataProvider pokemonMetadataProvider = mock(IPokemonMetadataProvider.class);
        IPokedex pokedex = new PokedexImplement(pokemonFactory, pokemonMetadataProvider);

        Pokemon pokemon1 = new Pokemon(0, "Bulbasaur", 126, 126, 90, 613, 64, 4000, 4, 0.91);
        Pokemon pokemon2 = new Pokemon(1, "Ivysaur", 156, 158, 120, 613, 64, 4000, 4, 0.91);

        pokedex.addPokemon(pokemon1);
        pokedex.addPokemon(pokemon2);

        assertEquals(2, pokedex.size());
    }
    @Test
    void testGetPokemonsUnmodifiable() {
        IPokedex pokedex = new PokedexImplement();

        List<Pokemon> pokemons = pokedex.getPokemons();
        assertThrows(UnsupportedOperationException.class, () -> {
            pokemons.add(new Pokemon(2, "Venusaur", 198, 200, 160, 613, 64, 4000, 4, 0.91));
        });
    }

}
