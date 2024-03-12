package fr.univavignon.pokedex.api;

public class PokedexFactory implements IPokedexFactory{
    @Override
    public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        if (metadataProvider == null || pokemonFactory == null)
            throw new IllegalArgumentException();
        return new PokedexImplement(pokemonFactory, metadataProvider);
    }
}
