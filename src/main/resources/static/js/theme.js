(() => {
  const key = 'agriq-theme';
  const getThemeText = t => {
    if (window.AgriQI18n) {
      return t === 'dark' ? window.AgriQI18n.t('theme_light') : window.AgriQI18n.t('theme_dark');
    }
    return t === 'dark' ? 'Light mode' : 'Dark mode';
  };
  const apply = t => {
    document.documentElement.dataset.theme = t;
    document.querySelectorAll('.theme-toggle').forEach(b => b.textContent = getThemeText(t));
  };
  apply(localStorage.getItem(key) || 'light');
  document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.primary-nav').forEach(n => {
      if (!n.querySelector('.theme-toggle')) {
        const b = document.createElement('button');
        b.className = 'theme-toggle';
        b.type = 'button';
        n.prepend(b);
      }
    });
    apply(document.documentElement.dataset.theme);
  });
  document.addEventListener('click', e => {
    if (!e.target.closest('.theme-toggle')) return;
    const t = document.documentElement.dataset.theme === 'dark' ? 'light' : 'dark';
    localStorage.setItem(key, t);
    apply(t);
  });
  window.addEventListener('agriq-lang-change', () => {
    apply(document.documentElement.dataset.theme);
  });
})();
