class AndroidAppLocaleManager(
    private val context: Context,
) : AppLocaleManager {

    private val localManager = context.getSystemService<LocaleManager>()

    override fun getLocale(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val locales = localManager?.applicationLocales ?: return "en"
            if (locales.isEmpty) "en" else
                locales[0]?.toLanguageTag()?.split("-")?.firstOrNull() ?: "en"
        } else {
            AppCompatDelegate.getApplicationLocales()
                .toLanguageTags().split("-")
                .firstOrNull() ?: "en"
        }
    }
}

@Composable
actual fun rememberAppLocale(): AppLang {
    val context = LocalContext.current
    val locale = AndroidAppLocaleManager(context).getLocale()
    return remember(locale) {
        locale.toApLang()
    }
}