package com.islamux.qissas_al_mustaghfireen.viewmodel

import androidx.lifecycle.ViewModel
import com.islamux.qissas_al_mustaghfireen.model.Story
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StoryViewModel : ViewModel() {

    private fun getSampleStories(): List<Story> {
        return listOf(
            Story(
                id = 1,
                title = "قصة آدم عليه السلام",
                excerpt = "أول البشر وأول من استغفر ربه...",
                fullText = "خلق الله آدم بيديه ونفخ فيه من روحه وأسجد له الملائكة. عصى آدم ربه بأكل الشجرة، ثم ندم واستغفر فقال: 'رَبَّنَا ظَلَمْنَا أَنفُسَنَا وَإِن لَّمْ تَغْفِرْ لَنَا وَتَرْحَمْنَا لَنَكُونَنَّ مِنَ الْخَاسِرِينَ'. فتاب الله عليه."
            ),
            Story(
                id = 2,
                title = "قصة يونس عليه السلام",
                excerpt = "نبي الله الذي ابتلعه الحوت فدعا ربه...",
                fullText = "أرسل الله يونس إلى قومه فدعاهم فلم يؤمنوا، فغضب وخرج من بينهم بغير إذن ربه. ركب سفينة فهاج البحر واقترعوا ليلقوا أحدهم فوقعت القرعة عليه. التقمه الحوت فدعا في الظلمات: 'لَّا إِلَٰهَ إِلَّا أَنتَ سُبْحَانَكَ إِنِّي كُنتُ مِنَ الظَّالِمِينَ'. فاستجاب الله له ونجاه."
            ),
            Story(
                id = 3,
                title = "قصة توبة كعب بن مالك",
                excerpt = "أحد الثلاثة الذين خلفوا في غزوة تبوك...",
                fullText = "تخلف كعب بن مالك وصاحباه عن غزوة تبوك بغير عذر. قاطعهم النبي صلى الله عليه وسلم والمسلمون خمسين ليلة حتى ضاقت عليهم الأرض بما رحبت. ثم صدقوا في توبتهم فأنزل الله فيهم قرآنا يتلى وأمر النبي بقبول توبتهم: 'وَعَلَى الثَّلَاثَةِ الَّذِينَ خُلِّفُوا حَتَّىٰ إِذَا ضَاقَتْ عَلَيْهِمُ الْأَرْضُ بِمَا رَحُبَتْ وَضَاقَتْ عَلَيْهِمْ أَنفُسُهُمْ وَظَنُّوا أَن لَّا مَلْجَأَ مِنَ اللَّهِ إِلَّا إِلَيْهِ ثُمَّ تَابَ عَلَيْهِمْ لِيَتُوبُوا ۚ إِنَّ اللَّهَ هُوَ التَّوَّابُ الرَّحِيمُ'. ففرحوا بتوبة الله عليهم."
            )
        )
    }

    private val _stories = MutableStateFlow<List<Story>>(getSampleStories())
    val stories: StateFlow<List<Story>> = _stories.asStateFlow()

    private val _selectedStory = MutableStateFlow<Story?>(null)
    val selectedStory: StateFlow<Story?> = _selectedStory.asStateFlow()

    fun selectStory(storyId: Int) {
        // Find the story from the current list of stories exposed by the StateFlow
        _selectedStory.value = _stories.value.find { it.id == storyId }
    }

    // The loadStories function is no longer needed as stories are loaded at initialization.
    // The storyList mutableList is also no longer needed.
}
