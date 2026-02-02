# 🎮 Minimal Moves

> Android logická hra zaměřená na dosažení cílového čísla pomocí minimálního počtu tahů.

Minimal Moves je mobilní aplikace vytvořená v **Kotlinu** s využitím **Jetpack Compose**  
a klasických Android komponent.  
Projekt slouží jako **školní práce** demonstrující návrh aplikace, architekturu **MVVM**
a práci s **lokální databází**.

---

## 🎯 Cíl projektu

- Vytvořit jednoduchou, ale zábavnou logickou hru
- Použít moderní Android technologie
- Oddělit logiku aplikace od uživatelského rozhraní
- Navrhnout přehlednou a rozšiřitelnou architekturu
- Splnit požadavky na práci s databází a seznamy dat

---

## 🧠 Princip hry

- Hráč dostane **počáteční číslo** a **cílové číslo**
- Má k dispozici omezenou sadu matematických operací
- Cílem je dosáhnout cílového čísla na **co nejméně tahů**
- Po dokončení hry je výsledek uložen do historie

---

## ⭐ Hodnocení hráče

Výsledek hry je ohodnocen pomocí hvězdiček:

| Počet tahů | Hodnocení |
|-----------|-----------|
| ≤ 10      | ⭐⭐⭐ |
| 11–20     | ⭐⭐ |
| 21+       | ⭐ |

---

## ✨ Funkce aplikace

- 🎲 Náhodné generování levelů
- ➕ Dynamické operace podle levelu
- ⭐ Hodnocení pomocí hvězdiček
- 🎉 Animace výhry (konfety)
- 📜 Historie odehraných her
- 🗑️ Mazání historie s potvrzovacím dialogem
- 🌙 Podpora tmavého režimu (system default)
- 🔄 Správná reakce UI na otočení zařízení

---

## 📜 Historie her (RecyclerView)

Historie odehraných her je zobrazena pomocí **RecyclerView**, který:

- efektivně zobrazuje seznam herních výsledků
- odděluje logiku seznamu od jeho vzhledu
- používá vlastní layout položky (`item_game_result.xml`)
- umožňuje snadné rozšíření (např. detail výsledku)

Každá položka historie obsahuje:
- počáteční hodnotu
- cílovou hodnotu
- počet tahů

Toto řešení odpovídá standardním Android postupům a splňuje požadavek na použití
**RecyclerView**.

---

## 🏗️ Architektura aplikace

Projekt je navržen podle architektury **MVVM (Model–View–ViewModel)**:

- **Model**
  - Datové třídy
  - Room databáze pro ukládání historie her

- **View**
  - Jetpack Compose obrazovky
  - XML layouty pro RecyclerView

- **ViewModel**
  - Obsahuje herní logiku
  - Pracuje se stavem hry
  - Zprostředkovává komunikaci mezi UI a databází

Tento přístup zajišťuje:
- lepší přehlednost kódu
- snadnější údržbu
- oddělení logiky od UI

---

## 🛠️ Použité technologie

- Kotlin
- Jetpack Compose
- RecyclerView
- Room Database
- MVVM architektura
- Material Design 3

---

## 📌 Poznámka

Projekt je vytvořen jako **školní práce** se zaměřením na:
- architekturu aplikace
- práci s databází
- správu stavu
- moderní Android vývoj
