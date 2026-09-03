/* MSP configuration (₹/kg). Single reusable prototype mapping for all AgriQ procurement & dashboard estimates. */
window.AgriQMSP = {
  rates: {
    "Paddy (Common)": 23.69,
    "Paddy (Grade A)": 23.89,
    "Jowar Hybrid": 36.99,
    "Jowar Maldandi": 37.49,
    "Bajra": 27.75,
    "Ragi": 48.86,
    "Maize": 24.00,
    "Tur/Arhar": 80.00,
    "Moong": 87.68,
    "Urad": 78.00,
    "Groundnut": 72.63,
    "Sunflower Seed": 77.21,
    "Soyabean Yellow": 53.28,
    "Sesamum": 98.46,
    "Nigerseed": 95.37,
    "Cotton Medium Staple": 77.10,
    "Wheat": 25.85,
    "Barley": 21.50,
    "Gram": 58.75,
    "Masur/Lentil": 70.00,
    "Rapeseed & Mustard": 62.00,
    "Safflower": 65.40
  },
  seasons: {
    "Paddy (Common)": "Kharif",
    "Paddy (Grade A)": "Kharif",
    "Jowar Hybrid": "Kharif",
    "Jowar Maldandi": "Rabi",
    "Bajra": "Kharif",
    "Ragi": "Kharif",
    "Maize": "Kharif",
    "Tur/Arhar": "Kharif",
    "Moong": "Kharif",
    "Urad": "Kharif",
    "Groundnut": "Kharif",
    "Sunflower Seed": "Kharif",
    "Soyabean Yellow": "Kharif",
    "Sesamum": "Kharif",
    "Nigerseed": "Kharif",
    "Cotton Medium Staple": "Kharif",
    "Wheat": "Rabi",
    "Barley": "Rabi",
    "Gram": "Rabi",
    "Masur/Lentil": "Rabi",
    "Rapeseed & Mustard": "Rabi",
    "Safflower": "Rabi"
  },
  grades: ["Grade A", "Grade B", "Grade C"],
  gradeMultipliers: {
    "Grade A": 1.00,
    "Grade B": 0.96,
    "Grade C": 0.92
  },
  key: 'agriq-procurement-estimate',
  normalise(crop) {
    return String(crop || '').trim().toLowerCase().replace(/[^a-z0-9]/g, '');
  },
  getBaseRate(crop) {
    if (!crop) return null;
    if (Object.prototype.hasOwnProperty.call(this.rates, crop)) return this.rates[crop];
    const norm = this.normalise(crop);
    for (const [name, rate] of Object.entries(this.rates)) {
      if (this.normalise(name) === norm) return rate;
    }
    return null;
  },
  get(crop, grade) {
    const base = this.getBaseRate(crop);
    if (base === null) return null;
    if (!grade) return base; // default to base rate (Grade A)

    const g = String(grade).trim();
    if (this.normalise(crop) === 'paddycommon' && g === 'Grade A') {
      return this.rates["Paddy (Grade A)"] || 23.89;
    }

    const mult = this.gradeMultipliers[g] !== undefined ? this.gradeMultipliers[g] : 1.00;
    return Number((base * mult).toFixed(2));
  },
  getCropName(crop) {
    if (!crop) return '';
    if (Object.prototype.hasOwnProperty.call(this.rates, crop)) return crop;
    const norm = this.normalise(crop);
    for (const name of Object.keys(this.rates)) {
      if (this.normalise(name) === norm) return name;
    }
    return crop;
  },
  getSeason(crop) {
    const canonical = this.getCropName(crop);
    return this.seasons[canonical] || 'Kharif';
  },
  getQuintal(crop, grade) {
    const rate = this.get(crop, grade);
    return rate === null ? null : Number((rate * 100).toFixed(2));
  },
  getAllCrops() {
    return Object.keys(this.rates).map(name => ({
      name,
      rateKg: this.rates[name],
      rateQuintal: this.rates[name] * 100,
      season: this.seasons[name] || 'Kharif'
    }));
  },
  save(selection) {
    try {
      localStorage.setItem(this.key, JSON.stringify(selection));
    } catch (e) {}
  },
  load() {
    try {
      return JSON.parse(localStorage.getItem(this.key)) || {};
    } catch {
      return {};
    }
  },
  estimate(crop, quantity, grade) {
    const rate = this.get(crop, grade);
    return rate === null ? null : Number((rate * (Number(quantity) || 0)).toFixed(2));
  }
};
