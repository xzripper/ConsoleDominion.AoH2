<h1 align="center"><img src="ConsoleDominion.jpg" width="250"><br>AoH2: Console Dominion (<b><a href="https://steamcommunity.com/sharedfiles/filedetails/?id=3563307293">Steam Page</a></b>)</h1>

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
- `RPAutoBuild [BUILDING] [MONEY] [EXCLUDE]` (`rpab`) - Automatically build a specified building in all your provinces.  
  - `[BUILDING]` = name of the building (e.g., `farm`, `library`)  
  - `[MONEY]` = amount of gold to spend  
  - `[EXCLUDE]` = list of province IDs to skip  
  - Deducts money upfront, builds where possible; reports built, skipped, and leftover money.
- `RPAutoDevelop [MONEY] [EXCLUDE]` (`rpad`) - Automatically invest in development across your provinces.  
  - `[MONEY]` = amount of gold to spend  
  - `[EXCLUDE]` = list of province IDs to skip  
  - Divides money evenly per province; skips provinces at max tech level; reports developed provinces and leftover money.
- `RPAutoDE [FILTER] [MONEY] [EXCLUDE]` (`rpade`) - Automatically invest in economy across your provinces.  
  - `[FILTER]` = minimum economy level to skip (0 = no filter)  
  - `[MONEY]` = amount of gold to spend  
  - `[EXCLUDE]` = list of province IDs to skip  
  - Divides money evenly per province; skips provinces above the filter; reports developed provinces and leftover money.
- `RPAutoFestival [MONEY] [EXCLUDE]` (`rpaf`) - Automatically hold festivals in your provinces.  
  - `[MONEY]` = amount of gold to spend  
  - `[EXCLUDE]` = list of province IDs to skip  
  - Skips provinces already at full happiness; reports festivals held, skipped, and money left.
- `pid` - Shows info about the active province.  
  - Outputs: `[Country Name], [Province Name]: [Province ID]`  
  - Only works on valid land provinces; fails on sea or wasteland provinces.
- `RPLoan [GOLD] [TENURE]` (`rpl`) - Take a loan from the bank; GOLD is the amount you want, TENURE is the repayment period in turns. The gold is added immediately to your treasury, and repayment per turn is automatically calculated based on interest. Repay the loan in the game loans tab. Example: `RPLoan 150000 60`.
- `RPLoanInfo` (`rpli`) - Show generic loan information for your country, including maximum/minimum gold per loan, maximum/minimum tenure, and the minimal/maximal interest percentages.
- `RPLoanInfo [GOLD] [TENURE]` (`rpli`) - Show specific loan details for a requested GOLD amount and TENURE. Displays the interest in percent, total interest in gold, and repayment per turn. Example: `RPLoanInfo 150000 60`.
- More coming in future!

Make sure to visit the Steam Page for illustrations and more information!

### Installation:
- Download <a href="https://www.7-zip.org/">7-Zip</a> (if you don't have it yet).
- Download the <a href="https://github.com/xzripper/ConsoleDominion.AoH2/releases/tag/v1.3.0-nonsteam">modification</a>.
- Go to Steam -> Library -> Age of History 2 -> Manage (Icon) -> Manage -> Browse local files.
- Create a copy of AoC2.exe (CTRL+C, CTRL+V) (Optional).
- AoC2.exe -> Right Click -> 7-Zip -> Open Archive -> Go to `age\of\civilizations2\jakowski\lukasz\` directory.
- Drop `Commands.class` in the directory. Confirm everything.
- Close everything, done!

Notes: This is my first modification for this game! Also, most likely this modification won't work with other major mods (especially commands like `revolt` or `ideology`), but that depends! Modification was written for `~1.25` game version!

<hr>

That's it! V1.3.1
