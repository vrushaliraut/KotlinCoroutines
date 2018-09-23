package remote

import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Retrofit
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException
import java.lang.reflect.Type


internal class StringConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>,
                                       retrofit: Retrofit): Converter<ResponseBody, *> {
        return object : Converter<ResponseBody, String> {
            @Throws(IOException::class)
            override fun convert(value: ResponseBody): String {
                return value.string()
            }
        }
    }

    override fun requestBodyConverter(type: Type,
                                      parameterAnnotations: Array<Annotation>, methodAnnotations: Array<Annotation>, retrofit: Retrofit): Converter<*, RequestBody> {
        return object : Converter<String, RequestBody> {
            @Throws(IOException::class)
            override fun convert(value: String): RequestBody {
                return RequestBody.create(MediaType.parse("text/plain"), value)
            }
        }
    }
}