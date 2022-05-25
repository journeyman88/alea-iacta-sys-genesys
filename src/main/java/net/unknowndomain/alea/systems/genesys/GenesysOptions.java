/*
 * Copyright 2022 Marco Bignami.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.unknowndomain.alea.systems.genesys;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import net.unknowndomain.alea.systems.RpgSystemOptions;
import net.unknowndomain.alea.systems.annotations.RpgSystemData;
import net.unknowndomain.alea.systems.annotations.RpgSystemOption;


/**
 *
 * @author journeyman
 */
@RpgSystemData(bundleName = "net.unknowndomain.alea.systems.genesys.RpgSystemBundle")
public class GenesysOptions extends RpgSystemOptions
{
    @RpgSystemOption(name = "ability", shortcode = "a", description = "genesys.options.ability", argName = "abilityDice")
    private Integer ability;
    @RpgSystemOption(name = "difficulty", shortcode = "d", description = "genesys.options.difficulty", argName = "difficultyDice")
    private Integer difficulty;
    @RpgSystemOption(name = "proficency", shortcode = "p", description = "genesys.options.proficency", argName = "proficencyDice")
    private Integer proficency;
    @RpgSystemOption(name = "challenge", shortcode = "c", description = "genesys.options.challenge", argName = "challengeDice")
    private Integer challenge;
    @RpgSystemOption(name = "boost", shortcode = "b", description = "genesys.options.boost", argName = "boostDice")
    private Integer boost;
    @RpgSystemOption(name = "setback", shortcode = "s", description = "genesys.options.setback", argName = "setbackDice")
    private Integer setback;
    
    @Override
    public boolean isValid()
    {
        return !(isHelp());
    }

    public Integer getAbility()
    {
        return ability;
    }

    public Integer getDifficulty()
    {
        return difficulty;
    }

    public Integer getChallenge()
    {
        return challenge;
    }

    public Collection<GenesysModifiers> getModifiers()
    {
        Set<GenesysModifiers> mods = new HashSet<>();
        if (isVerbose())
        {
            mods.add(GenesysModifiers.VERBOSE);
        }
        return mods;
    }

    public Integer getProficency()
    {
        return proficency;
    }

    public Integer getBoost()
    {
        return boost;
    }

    public Integer getSetback()
    {
        return setback;
    }
    
}