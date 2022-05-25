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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import net.unknowndomain.alea.random.SingleResult;
import net.unknowndomain.alea.random.customdice.CustomDice;
import net.unknowndomain.alea.roll.GenericResult;
import net.unknowndomain.alea.roll.GenericRoll;

/**
 *
 * @author journeyman
 */
public class GenesysRoll implements GenericRoll
{
    private final Locale lang; 
    private final List<CustomDice<Symbols>> dicePool; 
    protected final Set<GenesysModifiers> mods;

    public GenesysRoll(
            Locale lang, 
            Integer boost,
            Integer setback,
            Integer ability,
            Integer difficulty,
            Integer proficiency,
            Integer challenge, 
            Collection<GenesysModifiers> mod
    )
    {
        this.lang = lang;
        this.mods = new HashSet<>();
        this.mods.addAll(mod);
        this.dicePool = new LinkedList<>();
        if (boost != null)
        {
            for (int i=0; i<boost; i++)
            {
                dicePool.add(GenesysSystem.BOOST_DIE);
            }
        }
        if (setback != null)
        {
            for (int i=0; i<setback; i++)
            {
                dicePool.add(GenesysSystem.SETBACK_DIE);
            }
        }
        if (ability != null)
        {
            for (int i=0; i<ability; i++)
            {
                dicePool.add(GenesysSystem.ABILITY_DIE);
            }
        }
        if (difficulty != null)
        {
            for (int i=0; i<difficulty; i++)
            {
                dicePool.add(GenesysSystem.DIFFICULTY_DIE);
            }
        }
        if (proficiency != null)
        {
            for (int i=0; i<proficiency; i++)
            {
                dicePool.add(GenesysSystem.PROFICIENCY_DIE);
            }
        }
        if (challenge != null)
        {
            for (int i=0; i<challenge; i++)
            {
                dicePool.add(GenesysSystem.CHALLENGE_DIE);
            }
        }
    }

    @Override
    public GenericResult getResult()
    {
        List<SingleResult<List<Symbols>>> results = new ArrayList<>(dicePool.size());
        dicePool.forEach((CustomDice<Symbols> d) ->
        {
            d.nextResult().ifPresent((SingleResult<List<Symbols>> r) -> 
            { 
                results.add(r);
            });
        });
        GenesysResults res = new GenesysResults(results);
        res.setLang(lang);
        res.setVerbose(mods.contains(GenesysModifiers.VERBOSE));
        return res;
    }
    
}
