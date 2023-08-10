package wizmokeycloak.config.multitenancy;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtTokenParser {

    public static String extractAudienceFromToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getAudience().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // Sample JWT token (replace with your own JWT token)
        String jwtToken =
            "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJmZjBoS1Uzb21HUnpfWjB5cDd0eUpWT3N2M2kyYmQ2ZlV3T2R2TWFSQW44In0.eyJleHAiOjE2OTA4OTUxMDgsImlhdCI6MTY5MDg5MTUwOCwianRpIjoiYTk0NDIzZDItNGVhZi00NzRiLWIxYTgtOWExZjAyZmE1NDk2IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9teV9yZWFsbSIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI3ZTAxY2I2OC1jNDMzLTQ1ZmUtYTMzYy04MzAwMzY3YjEzNDQiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJteV9jbGllbnQiLCJzZXNzaW9uX3N0YXRlIjoiZDNkOTQ1YjEtOTdiYi00NzcyLWEwNmUtM2VkMGJkODEyMDYyIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjgwODAiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImRlZmF1bHQtcm9sZXMtbXlfcmVhbG0iLCJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsibXlfY2xpZW50Ijp7InJvbGVzIjpbIlJPTEVfVVNFUiJdfSwiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwic2lkIjoiZDNkOTQ1YjEtOTdiYi00NzcyLWEwNmUtM2VkMGJkODEyMDYyIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJ1c2VyQHVlbmdpbmUub3JnIn0.NTkUsJJajEeJEei1zAoAY3nkB8B7yuGDmieAirNcSCdhreUowCc1GPxy2YKPQ7LL36SQkN8EroyTAAiGPEu8NHdMvLzq4r1Tb5SybDQaZP2uMc0rn8NAFsyUTJV9zHpgRVsPjGmBQ0bpZpH7s2wszxHGZpywQsewwQLyfMthbkfZN01rHDXyI1kOlxEHXLZBlSvtzfu63LSycNeJShE9ewU3HHw7h6TPkRPnVWujnuPoIPW1lg1GHUOVWcfQTbMpseozVAJYZWTlC9qAQrOuB443Eu0AWptGA1toJtrDpsPdzo3sQ2W7-N1l8bq5bk6pgE7eTgR9tX0v6b1zyWYF-w";

        String audience = extractAudienceFromToken(jwtToken);
        if (audience != null) {
            System.out.println("Audience: " + audience);
        } else {
            System.out.println("Failed to extract audience from the token.");
        }
    }
}
