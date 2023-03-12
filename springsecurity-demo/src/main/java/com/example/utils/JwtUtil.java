package com.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @author 张丽璇
 * @date 2023/3/5
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtil {
	//有效期为
	public static final Long TTL = 60 * 60 * 1000L; //一个小时
	//设置秘钥明文
	public static final String JWT_KEY = "sangeng";

	public static String getUUID(){
		String token = UUID.randomUUID().toString().replaceAll("-", "");
		return token;
	}

	/**
	 * 生成jwt
	 * @param subject token中要存放的数据（json格式）
	 */
	public static String createJWT(String subject){
		JwtBuilder builder = getJwtBuilder(subject,null,getUUID()); //设置过期时间
		return builder.compact();
	}

	/**
	 * 生成jwt
	 * @param subject token中要存放的数据（json格式）,即要加密的数据，敏感信息一般不放
	 * @param ttlMillis  token超时时间
	 */
	public static String createJWT(String subject, Long ttlMillis){
		JwtBuilder builder = getJwtBuilder(subject,null,getUUID()); //设置过期时间
		return builder.compact();
	}

	public static String createJWT(String id, String subject, Long ttlMillis){
		JwtBuilder builder = getJwtBuilder(subject,null,id); //设置过期时间
		return builder.compact();
	}

	private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid){
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		SecretKey secretKey = generalKey();
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		if (ttlMillis == null){
			ttlMillis = JwtUtil.TTL;
		}
		//延长过期时间
		long expMillis = nowMillis + ttlMillis;
		Date expDate = new Date(expMillis);
		return Jwts.builder().setId(uuid) //唯一的ID
				.setSubject(subject)  //主题，可以是JSON数据
				.setIssuer("sg") //签发者
				.setIssuedAt(now) //签发时间
				.signWith(signatureAlgorithm,secretKey) //使用HS256对称加密算法签名，第二个参数为秘钥
				.setExpiration(expDate);
	}

	/**
	 * 生成加密后的秘钥 SecretKey
	 */
	private static SecretKey generalKey() {
		byte[] encodeKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
		SecretKey key = new SecretKeySpec(encodeKey,0, encodeKey.length, "AES");
		return key;
	}

	public static void main(String[] args) {
		//加密
		// String jwt = createJWT("2123");
		// System.out.println(jwt);
		//解密
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhZTg2OTYyOTBjOWE0Zjg3YmY5MTFkNDdjYTcyZjYxZCIsInN1YiI6IjIiLCJpc3MiOiJzZyIsImlhdCI6MTY3Nzk5MjIwMCwiZXhwIjoxNjc3OTk1ODAwfQ.gdf_dbeKcgWhirtScoNmyGADEt-aeLph1gljtxYM1wo";
		Claims claims = parseJWT(token);
		String subject = claims.getSubject();
		System.out.println(subject);
	}

	/**
	 * 解析
	 */
	public static Claims parseJWT(String jwt) {
		SecretKey secretKey = generalKey();
		return Jwts.parser().setSigningKey(secretKey)
				.parseClaimsJws(jwt)
				.getBody();
	}
}
