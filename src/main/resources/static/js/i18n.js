/**
 * AgriQ Centralized Translation / Internationalization (i18n) Module
 * Supports: English ('en'), हिन्दी ('hi'), मराठी ('mr')
 * Persists language preference in localStorage ('agriq-lang').
 * Provides instant in-place bidirectional switching between English, Hindi, and Marathi.
 */
window.AgriQI18n = (() => {
  const STORAGE_KEY = 'agriq-lang';

  const translations = {
    en: {
      // Navbar & Global
      app_name: 'AgriQ',
      nav_dashboard: 'Dashboard',
      nav_book_slot: 'Book Slot',
      nav_procurement: 'Procurement',
      nav_payment_status: 'Payment Status',
      nav_my_profile: 'My Profile',
      nav_logout: 'Logout',
      nav_menu: 'Menu',
      theme_light: 'Light mode',
      theme_dark: 'Dark mode',
      lang_label: 'Language',
      farmer_services_portal: 'Farmer services portal',
      btn_refresh: 'Refresh',
      btn_save: 'Save Changes',
      btn_cancel: 'Cancel',
      btn_procure: 'Procure',

      // Common Fields
      label_mobile_number: 'Mobile Number',
      placeholder_mobile: 'Enter 10-digit mobile number',
      label_pin: '4-digit PIN',
      btn_login: 'Login',
      login_mobile_label: 'Mobile Number',
      login_mobile_placeholder: 'Enter 10-digit mobile number',
      login_pin_label: '4-digit PIN',
      login_btn: 'Login',
      login_or: 'or',

      // Login Page
      login_hero_badge: 'Farmer Services Portal',
      login_hero_title: 'Empowering Farmers, Simplifying Procurement',
      login_hero_desc: 'Book procurement slots, track your queue, and manage your agricultural journey from one place.',
      login_feature_1_title: 'Easy Slot Booking',
      login_feature_1_desc: 'Schedule timely mandi drop-offs',
      login_feature_2_title: 'Transparent MSP',
      login_feature_2_desc: 'Fair rates & guaranteed support',
      login_feature_3_title: 'Faster Procurement',
      login_feature_3_desc: 'Real-time digital token tracking',
      login_hero_footer: '© AgriQ Platform. Supporting Indian Agriculture.',
      login_welcome_back: 'Welcome Back',
      login_title: 'Farmer Login',
      login_subtext: 'Enter your registered mobile number and PIN to access your account.',
      login_new_farmer: 'New Farmer?',
      login_register_here: 'Register here',
      login_admin_link: 'Admin login',
      login_footer: 'AgriQ Farmer Portal • Secure Access',
      login_logging_in: 'Logging in...',
      login_success: 'Login successful. Redirecting...',
      login_failed: 'Login failed. Please check your mobile number and PIN.',
      login_network_error: 'Unable to connect to server. Please try again.',

      // Registration Page
      reg_title: 'Farmer Registration',
      reg_name_label: 'Farmer Name',
      reg_name_placeholder: 'Enter your full name',
      reg_btn: 'Register',
      reg_back_login: 'Back to Login',
      reg_success: 'Registration successful. Redirecting to login...',
      reg_failed: 'Registration failed. Please try again.',
      reg_network_error: 'Unable to connect. Please try again.',

      // Dashboard Page
      dash_greeting: 'Good day,',
      dash_subtitle: 'Manage your procurement journey, track queue tokens, and view government MSP rates.',
      dash_queue_label: 'Today’s queue',
      dash_queue_no_token: 'No active token',
      dash_queue_desc_default: 'Book a slot to receive your queue token.',
      dash_queue_unavailable: 'Queue status is currently unavailable.',
      dash_msp_summary_label: 'Government MSP Summary',
      dash_no_crop_selected: 'No crop selected yet.',
      dash_update_procurement: 'Update procurement',
      dash_selected_crop: 'Selected Crop',
      dash_msp_per_kg: 'MSP per kg',
      dash_total_quantity: 'Total quantity',
      dash_estimated_value: 'Estimated procurement value',
      dash_gov_benchmark: 'Government Benchmark',
      dash_gov_msp_rates: 'Government MSP Rates',
      dash_gov_msp_subtitle: 'Official Minimum Support Prices (MSP) approved for procurement across Kharif and Rabi agricultural seasons.',
      dash_search_placeholder: 'Search crops (e.g. Wheat, Paddy, Cotton)...',
      dash_all_seasons: 'All Seasons',
      dash_kharif: 'Kharif',
      dash_rabi: 'Rabi',
      dash_showing_crops: 'Showing {n} crop(s)',
      dash_no_crops_match: 'No crops match your search criteria.',
      th_crop: 'Crop',
      th_season: 'Season',
      th_msp_kg: 'MSP per kg',
      th_msp_quintal: 'MSP per quintal',
      th_action: 'Action',
      status_waiting: 'WAITING',
      status_serving: 'SERVING',
      status_completed: 'COMPLETED',

      // Booking Page
      booking_title: 'Book a procurement slot',
      booking_subtitle: 'Schedule a convenient mandi visit and track your appointment history.',
      booking_form_heading: 'Slot details',
      booking_centre_label: 'Procurement Centre',
      booking_loading_centres: 'Loading centres...',
      booking_select_centre: 'Select a procurement centre',
      booking_unable_load_centres: 'Procurement centres could not be loaded. Please try again.',
      booking_location_label: 'Centre location',
      booking_date_label: 'Booking Date',
      booking_time_label: 'Time Slot',
      booking_select_time: 'Select a time slot',
      booking_submit_btn: 'Book Slot',
      booking_success_title: 'Slot booked successfully',
      booking_date_prefix: 'Booking date:',
      booking_token_prefix: 'Token Number:',
      booking_status_prefix: 'Queue Status:',
      booking_error: 'Unable to book the slot. Please try again.',
      booking_visits_eyebrow: 'Your visits',
      booking_visits_heading: 'My Bookings',
      booking_loading: 'Loading your bookings...',
      booking_empty: 'You have no slot bookings yet.',
      booking_load_error: 'Unable to load bookings. Please try again.',

      // Procurement Page
      proc_title: 'Digital procurement recording',
      proc_subtitle: 'Record crop procurement and view your previous records.',
      proc_form_heading: 'Procurement details',
      proc_crop_label: 'Crop Name',
      proc_select_crop: 'Select a crop',
      proc_quantity_label: 'Quantity (kg)',
      proc_grade_label: 'Grade',
      proc_grade_a: 'Grade A (Premium)',
      proc_grade_b: 'Grade B (Standard FAQ)',
      proc_grade_c: 'Grade C (Commercial)',
      proc_msp_label: 'Government MSP per kg (₹)',
      proc_msp_placeholder: 'Auto-loaded on crop & grade',
      proc_total_label: 'Calculated total amount',
      proc_date_label: 'Procurement Date',
      proc_submit_btn: 'Record Procurement',
      proc_success: 'Procurement recorded successfully.',
      proc_error: 'Unable to record procurement. Please try again.',
      proc_invalid_qty: 'Please enter a valid quantity.',
      proc_msp_not_configured: 'Government MSP is not configured for this crop and grade.',
      proc_records_eyebrow: 'Your records',
      proc_records_heading: 'Previous Procurements',
      proc_loading: 'Loading procurement records...',
      proc_empty: 'No procurement records have been added.',
      th_quantity: 'Quantity',
      th_grade: 'Grade',
      th_total: 'Total',
      th_date: 'Date',

      // Payment Page
      pay_title: 'Payment status',
      pay_subtitle: 'Track your procurement payment records and their current status.',
      pay_form_heading: 'Record payment status',
      pay_amount_label: 'Amount (₹)',
      pay_date_label: 'Payment Date',
      pay_status_label: 'Payment Status',
      pay_opt_pending: 'Pending',
      pay_opt_processing: 'Processing',
      pay_opt_paid: 'Paid',
      pay_submit_btn: 'Record Payment',
      pay_success: 'Payment record saved successfully.',
      pay_error: 'Unable to save payment record. Please try again.',
      pay_history_heading: 'Payment History',
      pay_loading: 'Loading payment records...',
      pay_empty: 'No payment records found.',
      th_amount: 'Amount',
      th_status: 'Status',

      // Profile Page
      prof_title: 'My Profile',
      prof_subtitle: 'Manage and update your registered farmer identity, crop details, and mandi centre.',
      prof_card_label: 'Registered Farmer',
      prof_edit_btn: 'Edit Profile',
      prof_farmer_id: 'Farmer ID',
      prof_full_name: 'Full Name',
      prof_mobile: 'Mobile Number',
      prof_crop_details: 'Crop Details',
      prof_crop_placeholder: 'e.g. Wheat, Paddy, Mustard',
      prof_village: 'Village / Location',
      prof_village_placeholder: 'e.g. Rampur, Karnal',
      prof_centre: 'Office / Centre',
      prof_centre_placeholder: 'e.g. Mandi Procurement Centre #2',
      prof_not_provided: 'Not provided',
      prof_not_assigned: 'Not assigned',
      prof_saving: 'Saving profile...',
      prof_saved: 'Profile updated successfully!'
    },

    hi: {
      // Navbar & Global
      app_name: 'एग्रीक्यू',
      nav_dashboard: 'डैशबोर्ड',
      nav_book_slot: 'स्लॉट बुक करें',
      nav_procurement: 'खरीद',
      nav_payment_status: 'भुगतान स्थिति',
      nav_my_profile: 'मेरी प्रोफ़ाइल',
      nav_logout: 'लॉग आउट',
      nav_menu: 'मेनू',
      theme_light: 'लाइट मोड',
      theme_dark: 'डार्क मोड',
      lang_label: 'भाषा',
      farmer_services_portal: 'किसान सेवा पोर्टल',
      btn_refresh: 'रिफ्रेश',
      btn_save: 'परिवर्तन सहेजें',
      btn_cancel: 'रद्द करें',
      btn_procure: 'खरीदें',

      // Common Fields
      label_mobile_number: 'मोबाइल नंबर',
      placeholder_mobile: '10 अंकों का मोबाइल नंबर दर्ज करें',
      label_pin: '4-अंकीय पिन',
      btn_login: 'लॉगिन करें',
      login_mobile_label: 'मोबाइल नंबर',
      login_mobile_placeholder: '10 अंकों का मोबाइल नंबर दर्ज करें',
      login_pin_label: '4-अंकीय पिन',
      login_btn: 'लॉगिन करें',
      login_or: 'या',

      // Login Page
      login_hero_badge: 'किसान सेवा पोर्टल',
      login_hero_title: 'किसानों का सशक्तिकरण, खरीद प्रक्रिया का सरलीकरण',
      login_hero_desc: 'खरीद स्लॉट बुक करें, अपनी कतार ट्रैक करें और अपनी कृषि यात्रा को एक ही स्थान से प्रबंधित करें।',
      login_feature_1_title: 'आसान स्लॉट बुकिंग',
      login_feature_1_desc: 'मंडी में समय पर फसल पहुंचाने का समय तय करें',
      login_feature_2_title: 'पारदर्शी एमएसपी (MSP)',
      login_feature_2_desc: 'उचित मूल्य और सुनिश्चित सरकारी सहायता',
      login_feature_3_title: 'त्वरित खरीद',
      login_feature_3_desc: 'वास्तविक समय में डिजिटल टोकन ट्रैकिंग',
      login_hero_footer: '© एग्रीक्यू प्लेटफॉर्म। भारतीय कृषि का समर्थक।',
      login_welcome_back: 'वापसी पर स्वागत',
      login_title: 'किसान लॉगिन',
      login_subtext: 'अपने पंजीकृत मोबाइल नंबर और 4-अंकीय पिन से अपने खाते में प्रवेश करें।',
      login_new_farmer: 'नए किसान?',
      login_register_here: 'यहाँ पंजीकरण करें',
      login_admin_link: 'व्यवस्थापक लॉगिन',
      login_footer: 'एग्रीक्यू किसान पोर्टल • सुरक्षित पहुंच',
      login_logging_in: 'लॉगिन किया जा रहा है...',
      login_success: 'लॉगिन सफल रहा। आगे बढ़ रहे हैं...',
      login_failed: 'लॉगिन विफल रहा। कृपया मोबाइल नंबर और पिन जांचें।',
      login_network_error: 'सर्वर से जुड़ने में असमर्थ। कृपया पुन: प्रयास करें।',

      // Registration Page
      reg_title: 'किसान पंजीकरण',
      reg_name_label: 'किसान का पूरा नाम',
      reg_name_placeholder: 'अपना पूरा नाम दर्ज करें',
      reg_btn: 'पंजीकरण करें',
      reg_back_login: 'लॉगिन पर वापस जाएं',
      reg_success: 'पंजीकरण सफल रहा। लॉगिन पर पुनर्निर्देशित किया जा रहा है...',
      reg_failed: 'पंजीकरण विफल रहा। कृपया पुन: प्रयास करें।',
      reg_network_error: 'कनेक्ट करने में असमर्थ। कृपया पुन: प्रयास करें।',

      // Dashboard Page
      dash_greeting: 'शुभ दिन,',
      dash_subtitle: 'अपनी खरीद प्रक्रिया प्रबंधित करें, कतार टोकन ट्रैक करें और सरकारी एमएसपी दरें देखें।',
      dash_queue_label: 'आज की कतार',
      dash_queue_no_token: 'कोई सक्रिय टोकन नहीं',
      dash_queue_desc_default: 'कतार टोकन प्राप्त करने के लिए स्लॉट बुक करें।',
      dash_queue_unavailable: 'कतार स्थिति वर्तमान में उपलब्ध नहीं है।',
      dash_msp_summary_label: 'सरकारी एमएसपी सारांश',
      dash_no_crop_selected: 'अभी कोई फसल चयनित नहीं।',
      dash_update_procurement: 'खरीद अपडेट करें',
      dash_selected_crop: 'चयनित फसल',
      dash_msp_per_kg: 'एमएसपी प्रति किग्रा',
      dash_total_quantity: 'कुल मात्रा',
      dash_estimated_value: 'अनुमानित खरीद मूल्य',
      dash_gov_benchmark: 'सरकारी बेंचमार्क',
      dash_gov_msp_rates: 'सरकारी एमएसपी दरें',
      dash_gov_msp_subtitle: 'खरीफ और रबी कृषि मौसम के लिए खरीद हेतु अनुमोदित आधिकारिक न्यूनतम समर्थन मूल्य (एमएसपी)।',
      dash_search_placeholder: 'फसल खोजें (उदा. गेहूं, धान, कपास)...',
      dash_all_seasons: 'सभी मौसम',
      dash_kharif: 'खरीफ',
      dash_rabi: 'रबी',
      dash_showing_crops: '{n} फसलें प्रदर्शित',
      dash_no_crops_match: 'आपकी खोज के अनुसार कोई फसल नहीं मिली।',
      th_crop: 'फसल',
      th_season: 'मौसम',
      th_msp_kg: 'एमएसपी प्रति किग्रा',
      th_msp_quintal: 'एमएसपी प्रति क्विंटल',
      th_action: 'कार्रवाई',
      status_waiting: 'प्रतीक्षारत',
      status_serving: 'सेवा जारी',
      status_completed: 'पूर्ण',

      // Booking Page
      booking_title: 'खरीद स्लॉट बुक करें',
      booking_subtitle: 'मंडी यात्रा का समय निर्धारित करें और अपना अपॉइंटमेंट इतिहास देखें।',
      booking_form_heading: 'स्लॉट विवरण',
      booking_centre_label: 'खरीद केंद्र',
      booking_loading_centres: 'केंद्र लोड हो रहे हैं...',
      booking_select_centre: 'खरीद केंद्र चुनें',
      booking_unable_load_centres: 'खरीद केंद्र लोड नहीं किए जा सके। कृपया पुन: प्रयास करें।',
      booking_location_label: 'केंद्र का स्थान',
      booking_date_label: 'बुकिंग तिथि',
      booking_time_label: 'समय स्लॉट',
      booking_select_time: 'समय स्लॉट चुनें',
      booking_submit_btn: 'स्लॉट बुक करें',
      booking_success_title: 'स्लॉट सफलतापूर्वक बुक हो गया',
      booking_date_prefix: 'बुकिंग तिथि:',
      booking_token_prefix: 'टोकन संख्या:',
      booking_status_prefix: 'कतार स्थिति:',
      booking_error: 'स्लॉट बुक करने में असमर्थ। कृपया पुन: प्रयास करें।',
      booking_visits_eyebrow: 'आपकी यात्राएं',
      booking_visits_heading: 'मेरी बुकिंग्स',
      booking_loading: 'आपकी बुकिंग लोड हो रही हैं...',
      booking_empty: 'आपकी अभी तक कोई स्लॉट बुकिंग नहीं है।',
      booking_load_error: 'बुकिंग लोड करने में असमर्थ। कृपया पुन: प्रयास करें।',

      // Procurement Page
      proc_title: 'डिजिटल खरीद रिकॉर्डिंग',
      proc_subtitle: 'फसल खरीद दर्ज करें और अपने पिछले रिकॉर्ड देखें।',
      proc_form_heading: 'खरीद विवरण',
      proc_crop_label: 'फसल का नाम',
      proc_select_crop: 'फसल चुनें',
      proc_quantity_label: 'मात्रा (किग्रा)',
      proc_grade_label: 'ग्रेड',
      proc_grade_a: 'ग्रेड ए (प्रीमियम)',
      proc_grade_b: 'ग्रेड बी (मानक FAQ)',
      proc_grade_c: 'ग्रेड सी (व्यावसायिक)',
      proc_msp_label: 'सरकारी एमएसपी प्रति किग्रा (₹)',
      proc_msp_placeholder: 'फसल व ग्रेड पर स्वतः लोड',
      proc_total_label: 'कुल परिकलित राशि',
      proc_date_label: 'खरीद तिथि',
      proc_submit_btn: 'खरीद दर्ज करें',
      proc_success: 'खरीद सफलतापूर्वक दर्ज की गई।',
      proc_error: 'खरीद दर्ज करने में असमर्थ। कृपया पुन: प्रयास करें।',
      proc_invalid_qty: 'कृपया मान्य मात्रा दर्ज करें।',
      proc_msp_not_configured: 'इस फसल और ग्रेड के लिए एमएसपी कॉन्फ़िगर नहीं है।',
      proc_records_eyebrow: 'आपके रिकॉर्ड',
      proc_records_heading: 'पिछली खरीद',
      proc_loading: 'खरीद रिकॉर्ड लोड हो रहे हैं...',
      proc_empty: 'कोई खरीद रिकॉर्ड नहीं मिला।',
      th_quantity: 'मात्रा',
      th_grade: 'ग्रेड',
      th_total: 'कुल',
      th_date: 'तारीख',

      // Payment Page
      pay_title: 'भुगतान स्थिति',
      pay_subtitle: 'अपने खरीद भुगतान रिकॉर्ड और उनकी वर्तमान स्थिति ट्रैक करें।',
      pay_form_heading: 'भुगतान स्थिति दर्ज करें',
      pay_amount_label: 'राशि (₹)',
      pay_date_label: 'भुगतान तिथि',
      pay_status_label: 'भुगतान स्थिति',
      pay_opt_pending: 'लंबित',
      pay_opt_processing: 'प्रक्रियाधीन',
      pay_opt_paid: 'भुगतान पूर्ण',
      pay_submit_btn: 'भुगतान रिकॉर्ड करें',
      pay_success: 'भुगतान रिकॉर्ड सफलतापूर्वक सहेजा गया।',
      pay_error: 'भुगतान रिकॉर्ड सहेजने में असमर्थ। कृपया पुन: प्रयास करें।',
      pay_history_heading: 'भुगतान इतिहास',
      pay_loading: 'भुगतान रिकॉर्ड लोड हो रहे हैं...',
      pay_empty: 'कोई भुगतान रिकॉर्ड नहीं मिला।',
      th_amount: 'राशि',
      th_status: 'स्थिति',

      // Profile Page
      prof_title: 'मेरी प्रोफ़ाइल',
      prof_subtitle: 'अपनी पंजीकृत किसान पहचान, फसल विवरण और मंडी केंद्र को प्रबंधित व अपडेट करें।',
      prof_card_label: 'पंजीकृत किसान',
      prof_edit_btn: 'प्रोफ़ाइल संपादित करें',
      prof_farmer_id: 'किसान आईडी',
      prof_full_name: 'पूरा नाम',
      prof_mobile: 'मोबाइल नंबर',
      prof_crop_details: 'फसल विवरण',
      prof_crop_placeholder: 'उदा. गेहूं, धान, सरसों',
      prof_village: 'गांव / स्थान',
      prof_village_placeholder: 'उदा. रामपुर, करनाल',
      prof_centre: 'कार्यालय / केंद्र',
      prof_centre_placeholder: 'उदा. मंडी खरीद केंद्र #2',
      prof_not_provided: 'उपलब्ध नहीं',
      prof_not_assigned: 'आवंटित नहीं',
      prof_saving: 'प्रोफ़ाइल सहेजी जा रही है...',
      prof_saved: 'प्रोफ़ाइल सफलतापूर्वक अपडेट की गई!'
    },

    mr: {
      // Navbar & Global
      app_name: 'अ‍ॅग्रीक्यू',
      nav_dashboard: 'डॅशबोर्ड',
      nav_book_slot: 'स्लॉट बुक करा',
      nav_procurement: 'खरेदी',
      nav_payment_status: 'पेमेंट स्थिती',
      nav_my_profile: 'माझी प्रोफाइल',
      nav_logout: 'लॉग आउट',
      nav_menu: 'मेनू',
      theme_light: 'लाइट मोड',
      theme_dark: 'डार्क मोड',
      lang_label: 'भाषा',
      farmer_services_portal: 'शेतकरी सेवा पोर्टल',
      btn_refresh: 'रिफ्रेश',
      btn_save: 'बदल जतन करा',
      btn_cancel: 'रद्द करा',
      btn_procure: 'खरेदी करा',

      // Common Fields
      label_mobile_number: 'मोबाइल नंबर',
      placeholder_mobile: '10 अंकी मोबाइल नंबर टाका',
      label_pin: '4-अंकी पिन',
      btn_login: 'लॉगिन करा',
      login_mobile_label: 'मोबाइल नंबर',
      login_mobile_placeholder: '10 अंकी मोबाइल नंबर टाका',
      login_pin_label: '4-अंकी पिन',
      login_btn: 'लॉगिन करा',
      login_or: 'किंवा',

      // Login Page
      login_hero_badge: 'शेतकरी सेवा पोर्टल',
      login_hero_title: 'शेतकऱ्यांचे सक्षमीकरण, खरेदी प्रक्रियेचे सुलभीकरण',
      login_hero_desc: 'खरेदी स्लॉट बुक करा, तुमची रांग ट्रॅक करा आणि तुमचा शेती प्रवास एकाच ठिकाणाहून व्यवस्थापित करा.',
      login_feature_1_title: 'सुलभ स्लॉट बुकिंग',
      login_feature_1_desc: 'मार्केटमध्ये वेळेवर शेतमाल पोहोचवण्याचे नियोजन करा',
      login_feature_2_title: 'पारदर्शक हमीभाव (MSP)',
      login_feature_2_desc: 'योग्य दर आणि हमीपूर्वक सरकारी साहाय्य',
      login_feature_3_title: 'जलद खरेदी',
      login_feature_3_desc: 'रिअल-टाइम डिजिटल टोकन ट्रॅकिंग',
      login_hero_footer: '© अ‍ॅग्रीक्यू प्लॅटफॉर्म. भारतीय शेतीचा समर्थक.',
      login_welcome_back: 'पुन्हा स्वागत आहे',
      login_title: 'शेतकरी लॉगिन',
      login_subtext: 'आपल्या खात्यात प्रवेश करण्यासाठी नोंदणीकृत मोबाइल नंबर आणि 4-अंकी पिन टाका.',
      login_new_farmer: 'नवीन शेतकरी?',
      login_register_here: 'येथे नोंदणी करा',
      login_admin_link: 'प्रशासक लॉगिन',
      login_footer: 'अ‍ॅग्रीक्यू शेतकरी पोर्टल • सुरक्षित प्रवेश',
      login_logging_in: 'लॉगिन करत आहे...',
      login_success: 'लॉगिन यशस्वी झाले. पुढे जात आहे...',
      login_failed: 'लॉगिन अयशस्वी. कृपया आपला मोबाइल नंबर आणि पिन तपासा.',
      login_network_error: 'सर्व्हरशी कनेक्ट करण्यात अक्षम. कृपया पुन्हा प्रयत्न करा.',

      // Registration Page
      reg_title: 'शेतकरी नोंदणी',
      reg_name_label: 'शेतकऱ्याचे पूर्ण नाव',
      reg_name_placeholder: 'आपले पूर्ण नाव टाका',
      reg_btn: 'नोंदणी करा',
      reg_back_login: 'लॉगिनवर परत जा',
      reg_success: 'नोंदणी यशस्वी झाली. लॉगिनवर जात आहे...',
      reg_failed: 'नोंदणी अयशस्वी. कृपया पुन्हा प्रयत्न करा.',
      reg_network_error: 'कनेक्ट करण्यात अक्षम. कृपया पुन्हा प्रयत्न करा.',

      // Dashboard Page
      dash_greeting: 'शुभ दिवस,',
      dash_subtitle: 'आपली खरेदी प्रक्रिया व्यवस्थापित करा, रांग टोकन ट्रॅक करा आणि सरकारी हमीभाव दर पहा.',
      dash_queue_label: 'आजची रांग',
      dash_queue_no_token: 'सक्रिय टोकन नाही',
      dash_queue_desc_default: 'रांग टोकन मिळवण्यासाठी स्लॉट बुक करा.',
      dash_queue_unavailable: 'रांग स्थिती सध्या उपलब्ध नाही.',
      dash_msp_summary_label: 'सरकारी हमीभाव सारांश',
      dash_no_crop_selected: 'अद्याप कोणतेही पीक निवडलेले नाही.',
      dash_update_procurement: 'खरेदी अपडेट करा',
      dash_selected_crop: 'निवडलेले पीक',
      dash_msp_per_kg: 'हमीभाव प्रति किलो',
      dash_total_quantity: 'एकूण प्रमाण',
      dash_estimated_value: 'अंदाजे खरेदी मूल्य',
      dash_gov_benchmark: 'सरकारी मानके',
      dash_gov_msp_rates: 'सरकारी हमीभाव (MSP) दर',
      dash_gov_msp_subtitle: 'खरीप आणि रब्बी हंगामासाठी खरेदीकरिता मंजूर अधिकृत किमान आधारभूत भाव (MSP).',
      dash_search_placeholder: 'पीक शोधा (उदा. गहू, धान, कापूस)...',
      dash_all_seasons: 'सर्व हंगाम',
      dash_kharif: 'खरीप',
      dash_rabi: 'रब्बी',
      dash_showing_crops: '{n} पिके दाखवत आहे',
      dash_no_crops_match: 'आपल्या शोधानुसार कोणतेही पीक आढळले नाही.',
      th_crop: 'पीक',
      th_season: 'हंगाम',
      th_msp_kg: 'हमीभाव प्रति किलो',
      th_msp_quintal: 'हमीभाव प्रति क्विंटल',
      th_action: 'कृती',
      status_waiting: 'प्रतीक्षेत',
      status_serving: 'सेवा सुरू',
      status_completed: 'पूर्ण',

      // Booking Page
      booking_title: 'खरेदी स्लॉट बुक करा',
      booking_subtitle: 'मार्केट भेटीची सोयीस्कर वेळ ठरवा आणि आपला इतिहास पहा.',
      booking_form_heading: 'स्लॉट तपशील',
      booking_centre_label: 'खरेदी केंद्र',
      booking_loading_centres: 'केंद्र लोड होत आहेत...',
      booking_select_centre: 'खरेदी केंद्र निवडा',
      booking_unable_load_centres: 'खरेदी केंद्र लोड होऊ शकले नाहीत. कृपया पुन्हा प्रयत्न करा.',
      booking_location_label: 'केंद्राचे ठिकाण',
      booking_date_label: 'बुकिंग तारीख',
      booking_time_label: 'वेळ स्लॉट',
      booking_select_time: 'वेळ स्लॉट निवडा',
      booking_submit_btn: 'स्लॉट बुक करा',
      booking_success_title: 'स्लॉट यशस्वीरित्या बुक झाला',
      booking_date_prefix: 'बुकिंग तारीख:',
      booking_token_prefix: 'टोकन क्रमांक:',
      booking_status_prefix: 'रांग स्थिती:',
      booking_error: 'स्लॉट बुक करण्यात अक्षम. कृपया पुन्हा प्रयत्न करा.',
      booking_visits_eyebrow: 'तुमच्या भेटी',
      booking_visits_heading: 'माझी बुकिंग्स',
      booking_loading: 'बुकिंग लोड होत आहे...',
      booking_empty: 'तुमची अद्याप कोणतीही स्लॉट बुकिंग नाही.',
      booking_load_error: 'बुकिंग लोड करण्यात अक्षम. कृपया पुन्हा प्रयत्न करा.',

      // Procurement Page
      proc_title: 'डिजिटल खरेदी नोंदणी',
      proc_subtitle: 'पीक खरेदी नोंदवा आणि मागील नोंदी पहा.',
      proc_form_heading: 'खरेदी तपशील',
      proc_crop_label: 'पिकाचे नाव',
      proc_select_crop: 'पीक निवडा',
      proc_quantity_label: 'प्रमाण (किलो)',
      proc_grade_label: 'प्रत (Grade)',
      proc_grade_a: 'ग्रेड ए (प्रीमियम)',
      proc_grade_b: 'ग्रेड बी (मानक FAQ)',
      proc_grade_c: 'ग्रेड सी (व्यावसायिक)',
      proc_msp_label: 'सरकारी हमीभाव प्रति किलो (₹)',
      proc_msp_placeholder: 'पीक व ग्रेडनुसार आपोआप लोड',
      proc_total_label: 'एकूण गणलेली रक्कम',
      proc_date_label: 'खरेदी तारीख',
      proc_submit_btn: 'खरेदी नोंदवा',
      proc_success: 'खरेदी यशस्वीरित्या नोंदवली गेली.',
      proc_error: 'खरेदी नोंदवण्यात अक्षम. कृपया पुन्हा प्रयत्न करा.',
      proc_invalid_qty: 'कृपया वैध प्रमाण टाका.',
      proc_msp_not_configured: 'या पिकासाठी आणि ग्रेडसाठी हमीभाव उपलब्ध नाही.',
      proc_records_eyebrow: 'तुमच्या नोंदी',
      proc_records_heading: 'मागील खरेदी',
      proc_loading: 'खरेदी नोंदी लोड होत आहेत...',
      proc_empty: 'कोणतीही खरेदी नोंद आढळली नाही.',
      th_quantity: 'प्रमाण',
      th_grade: 'प्रत',
      th_total: 'एकूण',
      th_date: 'तारीख',

      // Payment Page
      pay_title: 'पेमेंट स्थिती',
      pay_subtitle: 'आपल्या खरेदी पेमेंट नोंदी आणि त्यांची सद्यस्थिती ट्रॅक करा.',
      pay_form_heading: 'पेमेंट स्थिती नोंदवा',
      pay_amount_label: 'रक्कम (₹)',
      pay_date_label: 'पेमेंट तारीख',
      pay_status_label: 'पेमेंट स्थिती',
      pay_opt_pending: 'प्रलंबित',
      pay_opt_processing: 'प्रक्रियेत',
      pay_opt_paid: 'भरणा पूर्ण',
      pay_submit_btn: 'पेमेंट नोंदवा',
      pay_success: 'पेमेंट नोंद यशस्वीरित्या सेव्ह झाली.',
      pay_error: 'पेमेंट नोंद सेव्ह करण्यात अक्षम. कृपया पुन्हा प्रयत्न करा.',
      pay_history_heading: 'पेमेंट इतिहास',
      pay_loading: 'पेमेंट नोंदी लोड होत आहेत...',
      pay_empty: 'कोणतीही पेमेंट नोंद आढळली नाही.',
      th_amount: 'रक्कम',
      th_status: 'स्थिती',

      // Profile Page
      prof_title: 'माझी प्रोफाइल',
      prof_subtitle: 'आपली नोंदणीकृत शेतकरी ओळख, पीक तपशील आणि मार्केट केंद्र व्यवस्थापित व अपडेट करा.',
      prof_card_label: 'नोंदणीकृत शेतकरी',
      prof_edit_btn: 'प्रोफाइल संपादित करा',
      prof_farmer_id: 'शेतकरी आयडी',
      prof_full_name: 'पूर्ण नाव',
      prof_mobile: 'मोबाइल नंबर',
      prof_crop_details: 'पीक तपशील',
      prof_crop_placeholder: 'उदा. गहू, धान, मोहरी',
      prof_village: 'गाव / ठिकाण',
      prof_village_placeholder: 'उदा. रामपूर, कर्नाल',
      prof_centre: 'कार्यालय / केंद्र',
      prof_centre_placeholder: 'उदा. मार्केट खरेदी केंद्र #2',
      prof_not_provided: 'उपलब्ध नाही',
      prof_not_assigned: 'नियुक्त नाही',
      prof_saving: 'प्रोफाइल जतन केली जात आहे...',
      prof_saved: 'प्रोफाइल यशस्वीरित्या अपडेट झाली!'
    }
  };

  let currentLang = localStorage.getItem(STORAGE_KEY) || 'en';
  if (!['en', 'hi', 'mr'].includes(currentLang)) currentLang = 'en';

  function t(key, fallback = '') {
    const dict = translations[currentLang] || translations.en;
    if (dict && dict[key] !== undefined) return dict[key];
    if (translations.en && translations.en[key] !== undefined) return translations.en[key];
    return fallback || key;
  }

  function applyLanguage(lang) {
    currentLang = ['en', 'hi', 'mr'].includes(lang) ? lang : 'en';
    localStorage.setItem(STORAGE_KEY, currentLang);
    document.documentElement.lang = currentLang;

    // 1. Synchronize all select elements
    document.querySelectorAll('.lang-select').forEach(select => {
      select.value = currentLang;
    });

    // 2. Translate all elements marked with data-i18n
    document.querySelectorAll('[data-i18n]').forEach(el => {
      const key = el.getAttribute('data-i18n');
      const val = t(key);
      if (val !== undefined && val !== null) {
        el.textContent = val;
      }
    });

    // 3. Translate placeholders
    document.querySelectorAll('[data-i18n-placeholder]').forEach(el => {
      const key = el.getAttribute('data-i18n-placeholder');
      const val = t(key);
      if (val) el.placeholder = val;
    });

    // 4. Translate titles / aria-labels
    document.querySelectorAll('[data-i18n-title]').forEach(el => {
      const key = el.getAttribute('data-i18n-title');
      const val = t(key);
      if (val) el.title = val;
    });

    // 5. Update Theme Toggle button text
    const currentTheme = document.documentElement.dataset.theme || 'light';
    document.querySelectorAll('.theme-toggle').forEach(btn => {
      btn.textContent = currentTheme === 'dark' ? t('theme_light') : t('theme_dark');
    });

    // 6. Notify active page to re-render dynamic tables, statuses, and counts
    window.dispatchEvent(new CustomEvent('agriq-lang-change', { detail: { lang: currentLang } }));
  }

  function setupSwitcher() {
    // 1. Attach direct change listeners to all existing .lang-select in the DOM
    document.querySelectorAll('.lang-select').forEach(select => {
      select.value = currentLang;
      select.onchange = e => applyLanguage(e.target.value);
    });

    // 2. Inject into .primary-nav if not present
    document.querySelectorAll('.primary-nav').forEach(nav => {
      if (!nav.querySelector('.lang-select')) {
        const select = document.createElement('select');
        select.className = 'lang-select';
        select.setAttribute('aria-label', 'Select Language');
        select.innerHTML = `
          <option value="en">English</option>
          <option value="hi">हिन्दी</option>
          <option value="mr">मराठी</option>
        `;
        select.value = currentLang;
        select.onchange = e => applyLanguage(e.target.value);
        const logoutBtn = nav.querySelector('.nav-logout');
        if (logoutBtn) {
          nav.insertBefore(select, logoutBtn);
        } else {
          nav.appendChild(select);
        }
      }
    });

    // 3. Inject into auth headers if missing
    document.querySelectorAll('.login-topbar, .site-header:not(:has(.primary-nav))').forEach(bar => {
      if (!bar.querySelector('.lang-select')) {
        const select = document.createElement('select');
        select.className = 'lang-select';
        select.setAttribute('aria-label', 'Select Language');
        select.innerHTML = `
          <option value="en">English</option>
          <option value="hi">हिन्दी</option>
          <option value="mr">मराठी</option>
        `;
        select.value = currentLang;
        select.onchange = e => applyLanguage(e.target.value);
        const themeBtn = bar.querySelector('.theme-toggle');
        if (themeBtn) {
          bar.insertBefore(select, themeBtn);
        } else {
          bar.appendChild(select);
        }
      }
    });

    // Apply translations on load
    applyLanguage(currentLang);
  }

  // Delegated listener on document guarantees ANY language select element change triggers applyLanguage
  document.addEventListener('change', e => {
    if (e.target && (e.target.classList.contains('lang-select') || e.target.closest('.lang-select'))) {
      const select = e.target.classList.contains('lang-select') ? e.target : e.target.closest('.lang-select');
      applyLanguage(select.value);
    }
  });

  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', setupSwitcher);
  } else {
    setupSwitcher();
  }

  return {
    t,
    getLanguage: () => currentLang,
    setLanguage: applyLanguage,
    translations
  };
})();
