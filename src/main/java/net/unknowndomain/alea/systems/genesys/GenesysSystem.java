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

import net.unknowndomain.alea.systems.RpgSystemDescriptor;
import net.unknowndomain.alea.systems.genesys.dice.AbilityDie;
import net.unknowndomain.alea.systems.genesys.dice.BoostDie;
import net.unknowndomain.alea.systems.genesys.dice.ChallengeDie;
import net.unknowndomain.alea.systems.genesys.dice.DifficultyDie;
import net.unknowndomain.alea.systems.genesys.dice.ProficiencyDie;
import net.unknowndomain.alea.systems.genesys.dice.SetbackDie;

/**
 *
 * @author journeyman
 */
public class GenesysSystem extends RpgSystemDescriptor
{
    protected static final BoostDie BOOST_DIE = new BoostDie();
    protected static final AbilityDie ABILITY_DIE = new AbilityDie();
    protected static final ProficiencyDie PROFICIENCY_DIE = new ProficiencyDie();
    protected static final SetbackDie SETBACK_DIE = new SetbackDie();
    protected static final DifficultyDie DIFFICULTY_DIE = new DifficultyDie();
    protected static final ChallengeDie CHALLENGE_DIE = new ChallengeDie();
    
    public GenesysSystem()
    {
        super("Genesys", "gen", "genesys");
    }
    
}
