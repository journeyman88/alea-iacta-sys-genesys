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

import net.unknowndomain.alea.icon.AleaIcon;

/**
 *
 * @author journeyman
 */
public enum Symbols
{
    SUCCESS("success"),
    ADVANTAGE("advantage"),
    TRIUMPH("triumph"),
    FAILURE("failure"),
    THREAT("threat"),
    DESPAIR("despair");
    
    private final AleaIcon icon;
    
    private Symbols(String iconId)
    {
        GenesysSystem system = new GenesysSystem();
        icon = new AleaIcon(system.getShortcut(), iconId);
    }

    public AleaIcon getIcon()
    {
        return icon;
    }
    
}
