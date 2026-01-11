# Noobnouns
A very simple minecraft pronoun mod.

# Usage
In game type `/nouns pronouns <PRONOUNS>`, replacing `<PRONOUNS>` with your pronouns and thats it ! it will appear next to your name and save automatically ! you can do the same for your name but typing `/nouns name <NAME>`

# Server Admins
You will **require** the library [Composed](https://modrinth.com/mod/composed/versions) in verison `1.4.0` or later<br>
If you wish to change how pronouns are displayed you can edit `config/noobnouns.conf`, setting the `formattingString` or `playerListFormat` variable. Cetain tags will be replaced, they are:
- `%p` which will be replaced with their pronouns
- `%n` which will be replaced with their set name
- `%u` which is replaced with their minecraft username