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
            "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJaWnhidnAtYWw0WTYwZG16ejdUWkxkZFUzVkpRVWc3bGlxUnhDUUNrWXBnIn0.eyJleHAiOjE2OTE2NjE3MTksImlhdCI6MTY5MTY1ODExOSwiYXV0aF90aW1lIjoxNjkxNjU4MTEwLCJqdGkiOiI3ZGQ2MzZiYi1jZjg0LTQyNzQtOWIzMS1iOWNlYWFkNDI1YjgiLCJpc3MiOiJodHRwczovLzkwOTAtdWVuZ2luZXlzdy13aXptb2tleWNsb2EtMzRveDlycXYxYzYud3MtdXMxMDIuZ2l0cG9kLmlvL3JlYWxtcy9tYXN0ZXIiLCJhdWQiOlsibWFzdGVyLXJlYWxtIiwiYWNjb3VudCJdLCJzdWIiOiJkMTQ0OTcyYi1mODBjLTRhYmUtYTJkZi0zOTg1YjE5NjU5NjciLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiIxMnN0bWFsbCIsIm5vbmNlIjoiMzFDOEd1R1l4VlRISkg5VzhIQ2E2a01EMGJDUWNSZHVQeG9vUmFURTRRQSIsInNlc3Npb25fc3RhdGUiOiJiNzg3MjNkMC0xM2U3LTQxMTgtYmYwNi0xYWZhN2U2YmQ5YWUiLCJhY3IiOiIwIiwiYWxsb3dlZC1vcmlnaW5zIjpbIioiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImNyZWF0ZS1yZWFsbSIsImRlZmF1bHQtcm9sZXMtbWFzdGVyIiwib2ZmbGluZV9hY2Nlc3MiLCJhZG1pbiIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsibWFzdGVyLXJlYWxtIjp7InJvbGVzIjpbInZpZXctcmVhbG0iLCJ2aWV3LWlkZW50aXR5LXByb3ZpZGVycyIsIm1hbmFnZS1pZGVudGl0eS1wcm92aWRlcnMiLCJpbXBlcnNvbmF0aW9uIiwiY3JlYXRlLWNsaWVudCIsIm1hbmFnZS11c2VycyIsInF1ZXJ5LXJlYWxtcyIsInZpZXctYXV0aG9yaXphdGlvbiIsInF1ZXJ5LWNsaWVudHMiLCJxdWVyeS11c2VycyIsIm1hbmFnZS1ldmVudHMiLCJtYW5hZ2UtcmVhbG0iLCJ2aWV3LWV2ZW50cyIsInZpZXctdXNlcnMiLCJ2aWV3LWNsaWVudHMiLCJtYW5hZ2UtYXV0aG9yaXphdGlvbiIsIm1hbmFnZS1jbGllbnRzIiwicXVlcnktZ3JvdXBzIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBlbWFpbCBwcm9maWxlIiwic2lkIjoiYjc4NzIzZDAtMTNlNy00MTE4LWJmMDYtMWFmYTdlNmJkOWFlIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJhZG1pbiJ9.CcqKlUitq_TpAWB17nQDkp6VQHSIg7nnheWCXfPhpknFBqfYoDGDxxhZjyhvwDfc3C56MNY9WYrFyGQrRVXPiOY4uYYwO8XNg7vplHwXYF2d2EJ62c1826AeHV2bOA7r0lKNrro9yua3W2Epp3meIuT5HeDwbXKQXscK8Nwc1-FTy7oBBQFezRVCbOp9GrnJFav4jJX0kFYc-kBvvVzFyY5CrYnpTzAzBFYVFcd_pdCerL3oGVDRnUxYCVoUCc6heK9eIlDH0Oh8mu2tMcAP9CN1llgMOJTQqFgt9eySaCUKCfCWow13nKUZrZRC8X8LNHqUJLnLb948Z0DJexeR4w";

        String audience = extractAudienceFromToken(jwtToken);
        if (audience != null) {
            System.out.println("Audience: " + audience);
        } else {
            System.out.println("Failed to extract audience from the token.");
        }
    }
}
