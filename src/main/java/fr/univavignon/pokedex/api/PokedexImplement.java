package fr.univavignon.pokedex.api;

import java.util.*;

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
        for (Pokemon pokemon1 : pokemons) {
            if (pokemon1.getIndex() == pokemon.getIndex()) {
                throw new IllegalArgumentException("Pokemon already exists");
            }
        }
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
            for (Pokemon pokemon : pokemons) {
                if (pokemon.getIndex() == id) {
                    return pokemon;
                }
            }
            throw new PokedexException("Pokemon not found");
        }
    }

    @Override
    public List<Pokemon> getPokemons() {
        return Collections.unmodifiableList(this.pokemons);
    }

    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        this.pokemons.sort(order);
        return Collections.unmodifiableList(this.pokemons);
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        Pokemon pokemonIncomplet = this.pokemonFactory.createPokemon(index, cp, hp, dust, candy);
        try {
            this.pokemonMetadata = this.pokemonMetadataProvider.getPokemonMetadata(index);
            Pokemon PokemonComplet = new Pokemon(index, this.pokemonMetadata.getName(),
                    this.pokemonMetadata.getAttack(),
                    this.pokemonMetadata.getDefense(), this.pokemonMetadata.getStamina(),
                    pokemonIncomplet.getCp(), pokemonIncomplet.getHp(), pokemonIncomplet.getDust(),
                    pokemonIncomplet.getCandy(),
                    pokemonIncomplet.getIv());
            this.pokemons.add(PokemonComplet);
            return PokemonComplet;
        } catch (PokedexException e) {
            e.printStackTrace();
            }
        return null;

    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return this.pokemonMetadataProvider.getPokemonMetadata(index);
    }

}