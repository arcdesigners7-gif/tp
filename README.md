# Tp (Teleport HUD) — Fabric 1.21.8

Ye repository **Tp** mod ka source code hai — Minecraft 1.21.8 ke liye Fabric mod jo OP players ko screen ke right side me connected players dikhata hai, aur unpe click karne se OP us player ke paas teleport ho sakta hai.

## Jo main files hain (short)
- `build.gradle` - Fabric Loom 1.3.10 + dependencies
- `src/main/java/com/tp` - Java source (mod initializer + client + networking)
- `src/main/resources/fabric.mod.json` - mod metadata
- `.github/workflows` - GitHub Actions (build on push, attach jar on release)
- README yehi

## Har ek step — step-by-step (every single step)

### 1) ZIP ko GitHub pe upload karna (web UI - simplest)
1. GitHub me login karo.
2. "New repository" pe jao → name: `tp` (ya jo chaho) → Create repository.
3. Repo page me `Add file` → `Upload files` → ZIP extract karke sare files ko drag & drop karo (ya upload karo).
4. Commit changes (bottom me message likh ke `Commit changes`).

### 2) Git (recommended) — local -> push to GitHub
Agar tum comfortable ho terminal/git se, repo folder me ye commands run karo (replace `yourname` aur `reponame`):

```bash
git init
git add .
git commit -m "Initial commit - Tp mod"
git branch -M main
git remote add origin https://github.com/yourusername/your-repo-name.git
git push -u origin main
```

### 3) GitHub Actions se `.jar` build karna (automatic)
- Repository me `.github/workflows/build.yml` file included hai — ye push pe run karega aur build artifacts (build/libs/*.jar) ko Actions artifact me upload karega.
- Agar chaho release ke time pe jar ko Release assets me attach karna, `release.yml` workflow included hai; jab tum GitHub -> Releases -> "Draft a new release" -> publish karoge, ye workflow run karke jo jar banega usko release me attach karega.

### 4) Kaise download kare compiled jar (2 tariqe)
**A) From GitHub Actions (immediate)**
1. GitHub repo me `Actions` tab pe jao.
2. Latest build run open karo -> right side me `Artifacts` -> `tp-jar` -> download zip -> andar se `.jar` nikalo.

**B) From Release (recommended for distribution)**
1. GitHub -> `Releases` -> `Draft a new release` (tag like `v1.0.0`) -> `Publish release`.
2. `release.yml` workflow will run and attach the built jar to the Release. When finished, open the release and download the jar directly from assets.

### 5) Local build (agar tum local build karna chaho)
- Install Java 17 (Temurin/Adoptium recommended). Check `java -version`.
- Install Gradle (recommended) or use GitHub Actions (no local install required).
- In project folder run:
```bash
gradle build
```
- Output `.jar` will appear in `build/libs/`

*Note:* This ZIP does not include the Gradle wrapper jar. If you prefer wrapper locally, run `gradle wrapper` (requires Gradle installed) to generate `gradlew` scripts and wrapper jar. But you can always use the Actions build (no local Gradle required).

### 6) Game-side setup (testing the mod)
1. Install Fabric Loader for Minecraft 1.21.8 (version >= 0.14.22 recommended).
2. Put Fabric API (matching version) in `mods/` (e.g., fabric-api-0.88.0+1.21.8.jar).
3. Put the produced `tp-*.jar` in the same `mods/` folder.
4. Start Minecraft with Fabric profile; join a server (or run integrated server) and make sure you are OP on the server to see teleport buttons and use them.

## Questions / help
Agar kisi step me error aaye (Actions fail, build error, ya runtime problem), batao — mai waha ka stacktrace dekh ke fix bata dunga.
