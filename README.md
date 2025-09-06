<h1 align="center"><img src="ConsoleDominion.jpg" width="250"><br>AoH2: Console Dominion</h1>

Age of History 2 light modification that extends existing console commands, allowing for more interesting gameplay possibilities/filling gaps in game's unimplemented functionalities!

### New commands:
- `setowner [COUNTRY_ID/"me"]` - Set province's owner; click on province before running the command.
- `addmoney [NUMBER]` - Adds gold to the treasury; click on province (country) before running the command.
- `ideology [IDEOLOGY]` - Changes country ideology; click on province (country) before running the command.
  - Supported ideologies: `democracy`, `monarchy`, `communism`, `fascism`, `republic`, `horde`, `citystate`.
  - Known glitches: Sometimes text placed incorrectly (remove one character in country's name and type it back to fix it); Colors are different from original game's ideology colors (not that serious?)
- `removeplayer [First/Last]` - Remove previously added player. Use "first" (case ignorant) to remove player from start; use "last" to remove player from the end of the list.
- `revolt [COUNTRY_TAG]` - Create a revolt in a country; click on province before running the command.
  - How to get country tag? E.g if you want to make a revolt in France, always take first three characters from country's name (e.g `France` -> `fra`) and add a first letter of ideology after an underscore (E.g `Fascism` -> `_f`). So if you want to make a fascist revolt in France, use this country tag: `fra_f`. Other examples: `Fascist Italy` -> `ita_f`, `Monarchist Germany` -> `ger_m`, `Communist Poland` -> `pol_c`, ETC!
- More coming in future!

### Installation:
- Download <a href="https://www.7-zip.org/">7-Zip</a> (if you don't have it yet).
- Download the <a href="https://github.com/xzripper/ConsoleDominion.AoH2/releases/tag/v1.0.0-steam">modification</a>.
- Go to Steam -> Library -> Age of History 2 -> Manage (Icon) -> Manage -> Browse local files.
- Create a copy of AoC2.exe (CTRL+C, CTRL+V) (Optional).
- AoC2.exe -> Right Click -> 7-Zip -> Open Archive -> Go to `age\of\civilizations2\jakowski\lukasz\` directory.
- Drop `Commands.class` in the directory. Confirm everything.
- Close everything, done!

Notes: This is my first modification for this game! Also, most likely this modification won't work with other major mods (especially commands like `revolt` or `ideology`), but that depends! Modification was written for `1.25`-`1.24` game version!

<hr>

That's it! V1.0.0
