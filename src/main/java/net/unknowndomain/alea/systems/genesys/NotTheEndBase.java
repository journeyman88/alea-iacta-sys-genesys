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
import java.util.List;
import java.util.Optional;
import java.util.Locale;
import java.util.Set;
import net.unknowndomain.alea.random.SingleResult;
import net.unknowndomain.alea.roll.GenericRoll;

/**
 *
 * @author journeyman
 */
public abstract class NotTheEndBase implements GenericRoll
{
    private final Locale lang; 
    protected final Set<GenesysModifiers> mods;

    public NotTheEndBase(Locale lang, Collection<GenesysModifiers> mod)
    {
        this.lang = lang;
        this.mods = new HashSet<>();
        this.mods.addAll(mod);
    }
    
    protected abstract TokenDeck getBag();

    protected NotTheEndResults extractTokens(List<SingleResult<Tokens>> currentResult, int extract)
    {
        for (int i = 0; i < extract; i++)
        {
            Optional<SingleResult<Tokens>> tr = getBag().nextResult();
            if (tr.isPresent())
            {
                currentResult.add(tr.get());
            }
        }
        NotTheEndResults results = new NotTheEndResults(currentResult, getBag());
        results.setVerbose(mods.contains(GenesysModifiers.VERBOSE));
        results.setLang(lang);
        return results;
    }
    
}
