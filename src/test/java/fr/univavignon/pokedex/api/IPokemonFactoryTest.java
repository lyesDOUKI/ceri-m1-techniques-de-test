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
        //création d'un mock pour l'interface IPokemonFactory
        this.iPokemonFactory = mock(IPokemonFactory.class);
    }
    //test la création d'un pokemon avec les bonnes valeurs
    @Test
    public void createPokemonShouldReturnCorrectPokemonTest() {
        //création d'un pokemon avec un mock sur les valeurs
        when(this.iPokemonFactory.createPokemon(0,
                613, 64, 4000, 4)).
                thenReturn(new Pokemon(0,
                        "Bulbizarre", 130, 130,
                        100, 613, 64,
                        4000, 4, 0.91));
        //véfifier que les informations récupérées sont correctes
        Pokemon pokemon = this.iPokemonFactory.createPokemon(0, 613, 64,
                4000, 4);
        //faire les assertions sur les valeurs récupérées notamment attaque, defense et endurance
        //voir si elle sont entre 126 et 141 (attaque et defense)
        //et entre 90 et 105 (endurance)
        assertTrue(pokemon.getAttack() >= 126 && pokemon.getAttack() <= 141,
                "la valeur de l'attaque doit être entre 126 et 141");
        assertTrue(pokemon.getDefense() >= 126 && pokemon.getDefense() <= 141,
                "la valeur de la defense doit être entre 126 et 141");
        assertTrue(pokemon.getStamina() >= 90 && pokemon.getStamina() <= 105,
                "la valeur de l'endurance doit être entre 90 et 105");
    }
}
