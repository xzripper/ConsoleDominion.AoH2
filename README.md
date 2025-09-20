<h1 align="center"><img src="ConsoleDominion.jpg" width="250"><br>AoH2: Console Dominion (<del><b><a href="https://steamcommunity.com/sharedfiles/filedetails/?id=3563307293">Steam Page</a></b></del>)</h1>

Age of History 2 light modification that extends existing console commands, allowing for more interesting gameplay possibilities/filling gaps in game's unimplemented functionalities!

### New commands:
- `setowner [COUNTRY_ID/"me"]` - Set province's owner; click on the province before running the command.
- `addmoney [NUMBER]` - Adds gold to the treasury; click on the province (country) before running the command.
- `ideology [IDEOLOGY]` - Changes country ideology; click on the province (country) before running the command.
  - Supported ideologies: `democracy`, `monarchy`, `communism`, `fascism`, `republic`, `horde`, `citystate`.
  - Known glitches: Sometimes text placed incorrectly (remove one character in country's name and type it back to fix it); Colors are different from original game's ideology colors (not that serious?)
- `removeplayer [PlayerPosition]` - Remove previously added player.
- `revolt [COUNTRY_TAG]` - Create a revolt in a country; click on theprovince before running the command.
  - How to get country tag? E.g if you want to make a revolt in France, always take first three characters from country's name (e.g `France` -> `fra`) and add a first letter of ideology after an underscore (E.g `Fascism` -> `_f`). So if you want to make a fascist revolt in France, use this country tag: `fra_f`. Other examples: `Fascist Italy` -> `ita_f`, `Monarchist Germany` -> `ger_m`, `Communist Poland` -> `pol_c`, ETC! Use `province` on any country province to get the country tag if you can't compile a country's tag.
- `intervene [Against]` - Help a country with their war against another country. Click on the province of the country you want to help, and `Against` argument is the country ID you want to fight against (use `id` to get the country ID).
- `switchplayer` - Switch to another country. Click on the province of the country you want to switch to.
- `rpab [BUILDING] [MONEY] [EXCLUDE]` - Automatically build a specified building in all your provinces.  
  - `[BUILDING]` = name of the building (e.g., `farm`, `library`)  
  - `[MONEY]` = amount of gold to spend  
  - `[EXCLUDE]` = list of province IDs to skip  
  - Deducts money upfront, builds where possible; reports built, skipped, and leftover money.
- `rpaf [MONEY] [EXCLUDE]` - Automatically hold festivals in your provinces.  
  - `[MONEY]` = amount of gold to spend  
  - `[EXCLUDE]` = list of province IDs to skip  
  - Skips provinces already at full happiness; reports festivals held, skipped, and money left.
- `rpad [MONEY] [EXCLUDE]` - Automatically invest in development across your provinces.  
  - `[MONEY]` = amount of gold to spend  
  - `[EXCLUDE]` = list of province IDs to skip  
  - Divides money evenly per province; skips provinces at max tech level; reports developed provinces and leftover money.
- `rpade [FILTER] [MONEY] [EXCLUDE]` - Automatically invest in economy across your provinces.  
  - `[FILTER]` = minimum economy level to skip (0 = no filter)  
  - `[MONEY]` = amount of gold to spend  
  - `[EXCLUDE]` = list of province IDs to skip  
  - Divides money evenly per province; skips provinces above the filter; reports developed provinces and leftover money.
- `pid` - Shows info about the active province.  
  - Outputs: `[Country Name], [Province Name]: [Province ID]`  
  - Only works on valid land provinces; fails on sea or wasteland provinces.
- More coming in future!

Make sure to visit the Steam Page for illustrations and more information!

### Installation:
- Download <a href="https://www.7-zip.org/">7-Zip</a> (if you don't have it yet).
- Download the <a href="https://github.com/xzripper/ConsoleDominion.AoH2/releases/tag/v1.2.0-steam">modification</a>.
- Go to Steam -> Library -> Age of History 2 -> Manage (Icon) -> Manage -> Browse local files.
- Create a copy of AoC2.exe (CTRL+C, CTRL+V) (Optional).
- AoC2.exe -> Right Click -> 7-Zip -> Open Archive -> Go to `age\of\civilizations2\jakowski\lukasz\` directory.
- Drop `Commands.class` in the directory. Confirm everything.
- Close everything, done!

Notes: This is my first modification for this game! Also, most likely this modification won't work with other major mods (especially commands like `revolt` or `ideology`), but that depends! Modification was written for `~1.25` game version!

<hr>

That's it! V1.2.0 (Steam Page was Hidden).
