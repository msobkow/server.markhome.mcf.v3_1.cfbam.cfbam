// Description: Java 25 Instance Edit Object interface for CFBam NumberDef.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 *	
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public interface ICFBamNumberDefEditObj
	extends ICFBamNumberDefObj, ICFBamAtomEditObj
{
	/*
	 *	Get the original for this edition cast as the specified type.
	 *
	 *	@return The original, non-modifiable instance cast to a ICFBamNumberDefObj.
	 */
	ICFBamNumberDefObj getOrigAsNumberDef();

	/**
	 *	Get the required short attribute Digits.
	 *
	 *	@return	The required short attribute Digits.
	 */
	short getRequiredDigits();

	/**
	 *	Set the required short attribute Digits.
	 *
	 *	@param value The required short attribute Digits value to be applied.
	 */
	void setRequiredDigits(short value);

	/**
	 *	Get the required short attribute Precis.
	 *
	 *	@return	The required short attribute Precis.
	 */
	short getRequiredPrecis();

	/**
	 *	Set the required short attribute Precis.
	 *
	 *	@param value The required short attribute Precis value to be applied.
	 */
	void setRequiredPrecis(short value);

	/**
	 *	Get the optional BigDecimal attribute InitValue.
	 *
	 *	@return	The optional BigDecimal attribute InitValue.
	 */
	BigDecimal getOptionalInitValue();

	/**
	 *	Set the optional BigDecimal attribute InitValue.
	 *
	 *	@param value The optional BigDecimal attribute InitValue value to be applied.
	 */
	void setOptionalInitValue(BigDecimal value);

	/**
	 *	Get the optional BigDecimal attribute MinValue.
	 *
	 *	@return	The optional BigDecimal attribute MinValue.
	 */
	BigDecimal getOptionalMinValue();

	/**
	 *	Set the optional BigDecimal attribute MinValue.
	 *
	 *	@param value The optional BigDecimal attribute MinValue value to be applied.
	 */
	void setOptionalMinValue(BigDecimal value);

	/**
	 *	Get the optional BigDecimal attribute MaxValue.
	 *
	 *	@return	The optional BigDecimal attribute MaxValue.
	 */
	BigDecimal getOptionalMaxValue();

	/**
	 *	Set the optional BigDecimal attribute MaxValue.
	 *
	 *	@param value The optional BigDecimal attribute MaxValue value to be applied.
	 */
	void setOptionalMaxValue(BigDecimal value);

}
