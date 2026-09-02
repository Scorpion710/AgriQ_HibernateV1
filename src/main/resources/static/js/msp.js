/* MSP configuration (₹/kg). Keep crop rates in this single reusable mapping. */
window.AgriQMSP = {
  rates: {"paddy common":23.69,"paddy grade a":23.89,"jowar hybrid":36.99,"jowar maldandi":37.49,"bajra":27.75,"ragi":48.86,"maize":24,"tur/arhar":80,"moong":87.68,"urad":78,"groundnut":72.63,"sunflower seed":77.21,"soyabean yellow":53.28,"sesamum":98.46,"nigerseed":95.37,"cotton medium staple":77.1,"wheat":25.85,"barley":21.5,"gram":58.75,"masur/lentil":70,"rapeseed & mustard":62,"safflower":65.4},
  key: 'agriq-procurement-estimate',
  normalise(crop) { return String(crop || '').trim().toLowerCase(); },
  get(crop) { return this.rates[this.normalise(crop)] ?? null; },
  save(selection) { localStorage.setItem(this.key, JSON.stringify(selection)); },
  load() { try { return JSON.parse(localStorage.getItem(this.key)) || {}; } catch { return {}; } },
  estimate(crop, quantity) { const rate = this.get(crop); return rate === null ? null : rate * (Number(quantity) || 0); }
};
