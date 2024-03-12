package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PokedexImplement implements IPokedex{

    private List<Pokemon> pokemons;
    private PokemonMetadata pokemonMetadata;

    private IPokemonFactory pokemonFactory;
    private IPokemonMetadataProvider pokemonMetadataProvider;

    public static int CAPACITY = 151;
    public PokedexImplement() {
        this.pokemons = new ArrayList<>(CAPACITY);
    }
    public PokedexImplement(IPokemonFactory pokemonFactory, IPokemonMetadataProvider pokemonMetadataProvider) {
        this.pokemonFactory = pokemonFactory;
        this.pokemonMetadataProvider = pokemonMetadataProvider;
        this.pokemons = new ArrayList<>(CAPACITY);
    }
    @Override
    public int size() {
        return this.pokemons.size();
    }

    @Override
    public int addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
        return pokemon.getIndex();
    }

    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
       if(id < 0 || id >= CAPACITY)
       {
              throw new PokedexException("Invalid index");
         }
         else
         {
              return this.pokemons.get(id);
       }
    }

    @Override
    public List<Pokemon> getPokemons() {
        return this.pokemons;
    }

    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        this.pokemons.sort(order);
        return this.pokemons;
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        try {
            this.pokemonMetadata = this.getPokemonMetadata(index);
            Pokemon pokemon = new Pokemon(index, this.pokemonMetadata.getName(),
                    this.pokemonMetadata.getAttack(), this.pokemonMetadata.getDefense(),
                    this.pokemonMetadata.getStamina(), cp, hp, dust, candy, 0);
            this.pokemons.add(pokemon);
            return pokemon;
        } catch (PokedexException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        this.pokemonMetadata = this.getPokemonMetadata(index);
        return this.pokemonMetadata;
    }

}
