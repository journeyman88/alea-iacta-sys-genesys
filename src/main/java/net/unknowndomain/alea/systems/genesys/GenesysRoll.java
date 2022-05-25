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
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import net.unknowndomain.alea.random.GenericPool;
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
    private final List<GenericPool<CustomDice<Symbols>, List<Symbols>>> dicePools; 
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
        this.dicePools = new LinkedList<>();
        if (boost != null)
        {
            dicePools.add(new GenericPool<>(GenesysSystem.BOOST_DIE, boost));
        }
        if (setback != null)
        {
            dicePools.add(new GenericPool<>(GenesysSystem.SETBACK_DIE, setback));
        }
        if (ability != null)
        {
            dicePools.add(new GenericPool<>(GenesysSystem.ABILITY_DIE, ability));
        }
        if (difficulty != null)
        {
            dicePools.add(new GenericPool<>(GenesysSystem.DIFFICULTY_DIE, difficulty));
        }
        if (proficiency != null)
        {
            dicePools.add(new GenericPool<>(GenesysSystem.PROFICIENCY_DIE, proficiency));
        }
        if (challenge != null)
        {
            dicePools.add(new GenericPool<>(GenesysSystem.CHALLENGE_DIE, challenge));
        }
    }

    @Override
    public GenericResult getResult()
    {
        List<SingleResult<List<Symbols>>> results = new LinkedList<>();
        dicePools.forEach(p ->
        {
            results.addAll(p.getResults());
        });
        GenesysResults res = new GenesysResults(results);
        res.setLang(lang);
        res.setVerbose(mods.contains(GenesysModifiers.VERBOSE));
        return res;
    }
    
}
