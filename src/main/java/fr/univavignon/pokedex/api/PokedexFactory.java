package fr.univavignon.pokedex.api;

/**
 * Implementation of the IPokedexFactory interface.
 * Create a new PokedexImplement object.
 * @see IPokedexFactory
 */
public class PokedexFactory implements IPokedexFactory{
    @Override
    public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        if (metadataProvider == null || pokemonFactory == null)
            throw new IllegalArgumentException();
        return new PokedexImplement(pokemonFactory, metadataProvider);
    }
}
