package fr.univavignon.pokedex.api;

/**
 * implementation of the IPokemonTrainerFactory interface.
 * @see IPokemonTrainerFactory

 */
public class PokemonTrainerFactory implements IPokemonTrainerFactory{
    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
        return new PokemonTrainer(name, team,
                pokedexFactory.createPokedex(new PokemonMetadataProviderImpl(),
                        new PokemonFactory()));
    }
}
