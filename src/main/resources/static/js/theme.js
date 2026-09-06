// Dark mode removed. Clear any legacy localStorage theme preference.
(() => {
  try {
    localStorage.removeItem('agriq-theme');
    if (document.documentElement && document.documentElement.dataset) {
      delete document.documentElement.dataset.theme;
    }
  } catch (_) {}
})();
