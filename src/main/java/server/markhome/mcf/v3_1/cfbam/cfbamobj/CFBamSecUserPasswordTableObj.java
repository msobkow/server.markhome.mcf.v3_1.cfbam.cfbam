// Description: Java 25 Table Object implementation for SecUserPassword.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
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
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;

public class CFBamSecUserPasswordTableObj
	implements ICFBamSecUserPasswordTableObj
{
	protected ICFBamSchemaObj schema;
	private Map<CFLibDbKeyHash256, ICFSecSecUserPasswordObj> members;
	private Map<CFLibDbKeyHash256, ICFSecSecUserPasswordObj> allSecUserPassword;
	private Map< ICFSecSecUserPasswordBySetStampIdxKey,
		Map<CFLibDbKeyHash256, ICFSecSecUserPasswordObj > > indexBySetStampIdx;
	public static String TABLE_NAME = "SecUserPassword";
	public static String TABLE_DBNAME = "secuserpw";

	public CFBamSecUserPasswordTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFSecSecUserPasswordObj>();
		allSecUserPassword = null;
		indexBySetStampIdx = null;
	}

	public CFBamSecUserPasswordTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFSecSecUserPasswordObj>();
		allSecUserPassword = null;
		indexBySetStampIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecUserPasswordTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( CFSecSecUserPasswordTableObj.getBackingClassCode() );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( CFSecSecUserPasswordTableObj.getRuntimeClassCode() );
	}

	@Override
	public ICFSecSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFSecSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( null );
	}


	@Override
	public void minimizeMemory() {
		allSecUserPassword = null;
		indexBySetStampIdx = null;
		List<ICFSecSecUserPasswordObj> toForget = new LinkedList<ICFSecSecUserPasswordObj>();
		ICFSecSecUserPasswordObj cur = null;
		Iterator<ICFSecSecUserPasswordObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSecUserPasswordObj.
	 */
	@Override
	public ICFSecSecUserPasswordObj newInstance() {
		ICFSecSecUserPasswordObj inst = new CFBamSecUserPasswordObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSecUserPasswordObj.
	 */
	@Override
	public ICFSecSecUserPasswordEditObj newEditInstance( ICFSecSecUserPasswordObj orig ) {
		ICFSecSecUserPasswordEditObj edit = new CFBamSecUserPasswordEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecUserPasswordObj realiseSecUserPassword( ICFSecSecUserPasswordObj Obj ) {
		ICFSecSecUserPasswordObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFSecSecUserPasswordObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecUserPasswordObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexBySetStampIdx != null ) {
				ICFSecSecUserPasswordBySetStampIdxKey keySetStampIdx =
					schema.getCFSecBackingStore().getFactorySecUserPassword().newBySetStampIdxKey();
				keySetStampIdx.setRequiredPWSetStamp( keepObj.getRequiredPWSetStamp() );
				Map<CFLibDbKeyHash256, ICFSecSecUserPasswordObj > mapSetStampIdx = indexBySetStampIdx.get( keySetStampIdx );
				if( mapSetStampIdx != null ) {
					mapSetStampIdx.remove( keepObj.getPKey() );
					if( mapSetStampIdx.size() <= 0 ) {
						indexBySetStampIdx.remove( keySetStampIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexBySetStampIdx != null ) {
				ICFSecSecUserPasswordBySetStampIdxKey keySetStampIdx =
					schema.getCFSecBackingStore().getFactorySecUserPassword().newBySetStampIdxKey();
				keySetStampIdx.setRequiredPWSetStamp( keepObj.getRequiredPWSetStamp() );
				Map<CFLibDbKeyHash256, ICFSecSecUserPasswordObj > mapSetStampIdx = indexBySetStampIdx.get( keySetStampIdx );
				if( mapSetStampIdx != null ) {
					mapSetStampIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSecUserPassword != null ) {
				allSecUserPassword.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecUserPassword != null ) {
				allSecUserPassword.put( keepObj.getPKey(), keepObj );
			}

			if( indexBySetStampIdx != null ) {
				ICFSecSecUserPasswordBySetStampIdxKey keySetStampIdx =
					schema.getCFSecBackingStore().getFactorySecUserPassword().newBySetStampIdxKey();
				keySetStampIdx.setRequiredPWSetStamp( keepObj.getRequiredPWSetStamp() );
				Map<CFLibDbKeyHash256, ICFSecSecUserPasswordObj > mapSetStampIdx = indexBySetStampIdx.get( keySetStampIdx );
				if( mapSetStampIdx != null ) {
					mapSetStampIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecUserPasswordObj createSecUserPassword( ICFSecSecUserPasswordObj Obj ) {
		ICFSecSecUserPasswordObj obj = Obj;
		ICFSecSecUserPassword rec = obj.getSecUserPasswordRec();
		schema.getCFSecBackingStore().getTableSecUserPassword().createSecUserPassword(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecUserPasswordObj readSecUserPassword( CFLibDbKeyHash256 pkey ) {
		return( readSecUserPassword( pkey, false ) );
	}

	@Override
	public ICFSecSecUserPasswordObj readSecUserPassword( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFSecSecUserPasswordObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecUserPassword readRec = schema.getCFSecBackingStore().getTableSecUserPassword().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getSecUserPasswordTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecUserPasswordObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecUserPasswordObj readCachedSecUserPassword( CFLibDbKeyHash256 pkey ) {
		ICFSecSecUserPasswordObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecUserPassword( ICFSecSecUserPasswordObj obj )
	{
		final String S_ProcName = "CFBamSecUserPasswordTableObj.reallyDeepDisposeSecUserPassword() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFSecSecUserPasswordObj existing = readCachedSecUserPassword( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecUserPasswordBySetStampIdxKey keySetStampIdx = schema.getCFSecBackingStore().getFactorySecUserPassword().newBySetStampIdxKey();
		keySetStampIdx.setRequiredPWSetStamp( existing.getRequiredPWSetStamp() );



		if( indexBySetStampIdx != null ) {
			if( indexBySetStampIdx.containsKey( keySetStampIdx ) ) {
				indexBySetStampIdx.get( keySetStampIdx ).remove( pkey );
				if( indexBySetStampIdx.get( keySetStampIdx ).size() <= 0 ) {
					indexBySetStampIdx.remove( keySetStampIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeSecUserPassword( CFLibDbKeyHash256 pkey ) {
		ICFSecSecUserPasswordObj obj = readCachedSecUserPassword( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecUserPasswordObj lockSecUserPassword( CFLibDbKeyHash256 pkey ) {
		ICFSecSecUserPasswordObj locked = null;
		ICFSecSecUserPassword lockRec = schema.getCFSecBackingStore().getTableSecUserPassword().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecUserPasswordTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecUserPasswordObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecUserPassword", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecUserPasswordObj> readAllSecUserPassword() {
		return( readAllSecUserPassword( false ) );
	}

	@Override
	public List<ICFSecSecUserPasswordObj> readAllSecUserPassword( boolean forceRead ) {
		final String S_ProcName = "readAllSecUserPassword";
		if( ( allSecUserPassword == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFSecSecUserPasswordObj> map = new HashMap<CFLibDbKeyHash256,ICFSecSecUserPasswordObj>();
			allSecUserPassword = map;
			ICFSecSecUserPassword[] recList = schema.getCFSecBackingStore().getTableSecUserPassword().readAllDerived( null );
			ICFSecSecUserPassword rec;
			ICFSecSecUserPasswordObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecUserPasswordObj realised = (ICFSecSecUserPasswordObj)obj.realise();
			}
		}
		int len = allSecUserPassword.size();
		ICFSecSecUserPasswordObj arr[] = new ICFSecSecUserPasswordObj[len];
		Iterator<ICFSecSecUserPasswordObj> valIter = allSecUserPassword.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFSecSecUserPasswordObj> arrayList = new ArrayList<ICFSecSecUserPasswordObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecUserPasswordObj> cmp = new Comparator<ICFSecSecUserPasswordObj>() {
			@Override
			public int compare( ICFSecSecUserPasswordObj lhs, ICFSecSecUserPasswordObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecUserPasswordObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecUserPasswordObj> readCachedAllSecUserPassword() {
		final String S_ProcName = "readCachedAllSecUserPassword";
		ArrayList<ICFSecSecUserPasswordObj> arrayList = new ArrayList<ICFSecSecUserPasswordObj>();
		if( allSecUserPassword != null ) {
			int len = allSecUserPassword.size();
			ICFSecSecUserPasswordObj arr[] = new ICFSecSecUserPasswordObj[len];
			Iterator<ICFSecSecUserPasswordObj> valIter = allSecUserPassword.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFSecSecUserPasswordObj> cmp = new Comparator<ICFSecSecUserPasswordObj>() {
			public int compare( ICFSecSecUserPasswordObj lhs, ICFSecSecUserPasswordObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFSecSecUserPasswordObj readSecUserPasswordByIdIdx( CFLibDbKeyHash256 SecUserId )
	{
		return( readSecUserPasswordByIdIdx( SecUserId,
			false ) );
	}

	@Override
	public ICFSecSecUserPasswordObj readSecUserPasswordByIdIdx( CFLibDbKeyHash256 SecUserId, boolean forceRead )
	{
		ICFSecSecUserPasswordObj obj = readSecUserPassword( SecUserId, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecSecUserPasswordObj> readSecUserPasswordBySetStampIdx( LocalDateTime PWSetStamp )
	{
		return( readSecUserPasswordBySetStampIdx( PWSetStamp,
			false ) );
	}

	@Override
	public List<ICFSecSecUserPasswordObj> readSecUserPasswordBySetStampIdx( LocalDateTime PWSetStamp,
		boolean forceRead )
	{
		final String S_ProcName = "readSecUserPasswordBySetStampIdx";
		ICFSecSecUserPasswordBySetStampIdxKey key = schema.getCFSecBackingStore().getFactorySecUserPassword().newBySetStampIdxKey();
		key.setRequiredPWSetStamp( PWSetStamp );
		Map<CFLibDbKeyHash256, ICFSecSecUserPasswordObj> dict;
		if( indexBySetStampIdx == null ) {
			indexBySetStampIdx = new HashMap< ICFSecSecUserPasswordBySetStampIdxKey,
				Map< CFLibDbKeyHash256, ICFSecSecUserPasswordObj > >();
		}
		if( ( ! forceRead ) && indexBySetStampIdx.containsKey( key ) ) {
			dict = indexBySetStampIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFSecSecUserPasswordObj>();
			ICFSecSecUserPasswordObj obj;
			ICFSecSecUserPassword[] recList = schema.getCFSecBackingStore().getTableSecUserPassword().readDerivedBySetStampIdx( null,
				PWSetStamp );
			ICFSecSecUserPassword rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecUserPasswordTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecUserPasswordObj realised = (ICFSecSecUserPasswordObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySetStampIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecUserPasswordObj arr[] = new ICFSecSecUserPasswordObj[len];
		Iterator<ICFSecSecUserPasswordObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFSecSecUserPasswordObj> arrayList = new ArrayList<ICFSecSecUserPasswordObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecUserPasswordObj> cmp = new Comparator<ICFSecSecUserPasswordObj>() {
			@Override
			public int compare( ICFSecSecUserPasswordObj lhs, ICFSecSecUserPasswordObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecUserPasswordObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecUserPasswordObj readCachedSecUserPasswordByIdIdx( CFLibDbKeyHash256 SecUserId )
	{
		ICFSecSecUserPasswordObj obj = null;
		obj = readCachedSecUserPassword( SecUserId );
		return( obj );
	}

	@Override
	public List<ICFSecSecUserPasswordObj> readCachedSecUserPasswordBySetStampIdx( LocalDateTime PWSetStamp )
	{
		final String S_ProcName = "readCachedSecUserPasswordBySetStampIdx";
		ICFSecSecUserPasswordBySetStampIdxKey key = schema.getCFSecBackingStore().getFactorySecUserPassword().newBySetStampIdxKey();
		key.setRequiredPWSetStamp( PWSetStamp );
		ArrayList<ICFSecSecUserPasswordObj> arrayList = new ArrayList<ICFSecSecUserPasswordObj>();
		if( indexBySetStampIdx != null ) {
			Map<CFLibDbKeyHash256, ICFSecSecUserPasswordObj> dict;
			if( indexBySetStampIdx.containsKey( key ) ) {
				dict = indexBySetStampIdx.get( key );
				int len = dict.size();
				ICFSecSecUserPasswordObj arr[] = new ICFSecSecUserPasswordObj[len];
				Iterator<ICFSecSecUserPasswordObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFSecSecUserPasswordObj obj;
			Iterator<ICFSecSecUserPasswordObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecUserPasswordObj> cmp = new Comparator<ICFSecSecUserPasswordObj>() {
			@Override
			public int compare( ICFSecSecUserPasswordObj lhs, ICFSecSecUserPasswordObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeSecUserPasswordByIdIdx( CFLibDbKeyHash256 SecUserId )
	{
		ICFSecSecUserPasswordObj obj = readCachedSecUserPasswordByIdIdx( SecUserId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecUserPasswordBySetStampIdx( LocalDateTime PWSetStamp )
	{
		final String S_ProcName = "deepDisposeSecUserPasswordBySetStampIdx";
		ICFSecSecUserPasswordObj obj;
		List<ICFSecSecUserPasswordObj> arrayList = readCachedSecUserPasswordBySetStampIdx( PWSetStamp );
		if( arrayList != null )  {
			Iterator<ICFSecSecUserPasswordObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFSecSecUserPasswordObj updateSecUserPassword( ICFSecSecUserPasswordObj Obj ) {
		ICFSecSecUserPasswordObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecUserPassword().updateSecUserPassword( null,
			Obj.getSecUserPasswordRec() );
		obj = (ICFSecSecUserPasswordObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecUserPassword( ICFSecSecUserPasswordObj Obj ) {
		ICFSecSecUserPasswordObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecUserPassword().deleteSecUserPassword( null,
			obj.getSecUserPasswordRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecUserPasswordByIdIdx( CFLibDbKeyHash256 SecUserId )
	{
		ICFSecSecUserPasswordObj obj = readSecUserPassword(SecUserId);
		if( obj != null ) {
			ICFSecSecUserPasswordEditObj editObj = (ICFSecSecUserPasswordEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecUserPasswordEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeSecUserPasswordByIdIdx( SecUserId );
	}

	@Override
	public void deleteSecUserPasswordBySetStampIdx( LocalDateTime PWSetStamp )
	{
		ICFSecSecUserPasswordBySetStampIdxKey key = schema.getCFSecBackingStore().getFactorySecUserPassword().newBySetStampIdxKey();
		key.setRequiredPWSetStamp( PWSetStamp );
		if( indexBySetStampIdx == null ) {
			indexBySetStampIdx = new HashMap< ICFSecSecUserPasswordBySetStampIdxKey,
				Map< CFLibDbKeyHash256, ICFSecSecUserPasswordObj > >();
		}
		if( indexBySetStampIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFSecSecUserPasswordObj> dict = indexBySetStampIdx.get( key );
			schema.getCFSecBackingStore().getTableSecUserPassword().deleteSecUserPasswordBySetStampIdx( null,
				PWSetStamp );
			Iterator<ICFSecSecUserPasswordObj> iter = dict.values().iterator();
			ICFSecSecUserPasswordObj obj;
			List<ICFSecSecUserPasswordObj> toForget = new LinkedList<ICFSecSecUserPasswordObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexBySetStampIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecUserPassword().deleteSecUserPasswordBySetStampIdx( null,
				PWSetStamp );
		}
		deepDisposeSecUserPasswordBySetStampIdx( PWSetStamp );
	}
}