/*
 * Copyright 2022 m.bignami.
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
package net.unknowndomain.alea.systems.genesys.dice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.unknowndomain.alea.random.customdice.CustomDice;
import net.unknowndomain.alea.random.customdice.CustomFace;
import net.unknowndomain.alea.systems.genesys.Symbols;

/**
 *
 * @author m.bignami
 */
public class DifficultyDie extends CustomDice<Symbols>
{

    private final List<CustomFace<Symbols>> faces;
    
    public DifficultyDie()
    {
        List<CustomFace<Symbols>> tmp = new ArrayList<>(8);
        tmp.add(new CustomFace<>());
        tmp.add(new CustomFace<>(Symbols.FAILURE));
        tmp.add(new CustomFace<>(Symbols.FAILURE, Symbols.FAILURE));
        tmp.add(new CustomFace<>(Symbols.THREAT));
        tmp.add(new CustomFace<>(Symbols.THREAT));
        tmp.add(new CustomFace<>(Symbols.THREAT));
        tmp.add(new CustomFace<>(Symbols.THREAT, Symbols.THREAT));
        tmp.add(new CustomFace<>(Symbols.FAILURE, Symbols.THREAT));
        faces = Collections.unmodifiableList(tmp);
    }

    @Override
    public String getName()
    {
        return "DIFFICULTY";
    }

    @Override
    public List<CustomFace<Symbols>> getFaces()
    {
        return faces;
    }
    
}
