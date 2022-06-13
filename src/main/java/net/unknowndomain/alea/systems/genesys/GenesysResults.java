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
import java.util.Collections;
import java.util.List;
import net.unknowndomain.alea.messages.MsgBuilder;
import net.unknowndomain.alea.random.SingleResult;
import net.unknowndomain.alea.roll.LocalizedResult;

/**
 *
 * @author journeyman
 */
public class GenesysResults extends LocalizedResult
{
    private final static String BUNDLE_NAME = "net.unknowndomain.alea.systems.genesys.RpgSystemBundle";
    
    private final List<SingleResult<List<Symbols>>> results;
    private final List<Symbols> symbols;
    private GenesysResults prev;
    
    public GenesysResults(List<SingleResult<List<Symbols>>>  results)
    {
        List<SingleResult<List<Symbols>>>  tmp = new ArrayList<>(results.size());
        tmp.addAll(results);
        this.results = Collections.unmodifiableList(tmp);
        int adv = 0, suc = 0, tri = 0;
        for (SingleResult<List<Symbols>> t : results)
        {
            for (Symbols symbol : t.getValue())
            {
                switch(symbol)
                {
                    case ADVANTAGE:
                        adv++;
                        break;
                    case SUCCESS:
                        suc++;
                        break;
                    case TRIUMPH:
                        tri++;
                        break;
                    case THREAT:
                        adv--;
                        break;
                    case FAILURE:
                        suc--;
                        break;
                    case DESPAIR:
                        tri--;
                        break;
                }
            }
        }
        int size = Math.abs(adv) + Math.abs(suc) + Math.abs(tri);
        List<Symbols> tmp2 = new ArrayList<>(size);
        if (adv > 0)
        {
            tmp2.addAll(buildSymbols(Symbols.ADVANTAGE, adv));
        }
        if (adv < 0)
        {
            tmp2.addAll(buildSymbols(Symbols.THREAT, Math.abs(adv)));
        }
        if (suc > 0)
        {
            tmp2.addAll(buildSymbols(Symbols.SUCCESS, suc));
        }
        if (suc < 0)
        {
            tmp2.addAll(buildSymbols(Symbols.FAILURE, Math.abs(suc)));
        }
        if (tri > 0)
        {
            tmp2.addAll(buildSymbols(Symbols.TRIUMPH, tri));
        }
        if (tri < 0)
        {
            tmp2.addAll(buildSymbols(Symbols.DESPAIR, Math.abs(tri)));
        }
        symbols = Collections.unmodifiableList(tmp2);
    }
    
    private static List<Symbols> buildSymbols(Symbols symbol, int count)
    {
        List<Symbols> tmp = new ArrayList<>(count);
        for (int i=0; i<count; i++)
        {
            tmp.add(symbol);
        }
        return tmp;
    }

    @Override
    protected void formatResults(MsgBuilder messageBuilder, boolean verbose, int indentValue)
    {
        String indent = getIndent(indentValue);
        messageBuilder.append(indent);
        for (Symbols s : symbols)
        {
            messageBuilder.appendIcon(s.getIcon());
        }
        messageBuilder.appendNewLine();
        
        if (verbose)
        {
            messageBuilder.append(indent).append("Roll ID: ").append(getUuid()).appendNewLine();
            if (!results.isEmpty())
            {
                messageBuilder.append(indent).append(translate("genesys.results.results")).append(" [ ");
                for (SingleResult<List<Symbols>> t : results)
                {
                    messageBuilder.append("( ").append(t.getLabel()).append(" => ");
                    for (Symbols symbol : t.getValue())
                    {
                        messageBuilder.append(symbol.getIcon().getIconId()).append(" ");
                    }
                    messageBuilder.append(") ");
                }
                messageBuilder.append("]\n");
            }
            if (prev != null)
            {
                messageBuilder.append(indent).append(translate("genesys.results.prevResults")).append(" {\n");
                prev.formatResults(messageBuilder, verbose, indentValue + 4);
                messageBuilder.append(indent).append("}\n");
            }
        }
    }

    public GenesysResults getPrev()
    {
        return prev;
    }

    public void setPrev(GenesysResults prev)
    {
        this.prev = prev;
    }

    public List<SingleResult<List<Symbols>>> getResults()
    {
        return results;
    }

    @Override
    protected String getBundleName()
    {
        return BUNDLE_NAME;
    }

}
