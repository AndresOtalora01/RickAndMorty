package andres.otalora.rickandmorty

import andres.otalora.rickandmorty.data.model.CharacterDto
import andres.otalora.rickandmorty.data.remote.RickAndMortyApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RickAndMortyApiTest {
    private lateinit var server: MockWebServer
    private lateinit var api: RickAndMortyApi

    @Before
    fun setup() {
        server = MockWebServer()
        server.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(RickAndMortyApi::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun getCharactersByPage_returnsCharactersListDto() {
        // Enqueue a mock response
        server.enqueue(MockResponse().setBody(mockResponseJson))

        // Call the API method
        val page = 1
        val response = runBlocking { api.getCharactersByPage(page) }

        // Verify the response
        assertEquals(2, response.results.size)
        verifyCharacter(response.results[0], 1, "Rick Sanchez", "Alive", "Human", "", "Male",
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg")
        verifyCharacter(response.results[1], 2, "Morty Smith", "Alive", "Human", "", "Male",
            "https://rickandmortyapi.com/api/character/avatar/2.jpeg")

        // Verify the request sent to the server
        val recordedRequest = server.takeRequest()
        assertEquals("/character?page=$page", recordedRequest.path)
    }

    private fun verifyCharacter(
        character: CharacterDto,
        id: Int,
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String,
        image: String
    ) {
        assertEquals(id, character.id)
        assertEquals(name, character.name)
        assertEquals(status, character.status)
        assertEquals(species, character.species)
        assertEquals(type, character.type)
        assertEquals(gender, character.gender)
        assertEquals(image, character.image)
    }

    companion object {
        val mockResponseJson = """
            {
                "infoDto": {
                    "count": 2,
                    "pages": 1,
                    "next": null,
                    "prev": null
                },
                "results": [
                    {
                        "id": 1,
                        "name": "Rick Sanchez",
                        "status": "Alive",
                        "species": "Human",
                        "type": "",
                        "gender": "Male",
                        "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
                    },
                    {
                        "id": 2,
                        "name": "Morty Smith",
                        "status": "Alive",
                        "species": "Human",
                        "type": "",
                        "gender": "Male",
                        "image": "https://rickandmortyapi.com/api/character/avatar/2.jpeg"
                    }
                ]
            }
        """.trimIndent()
    }
}