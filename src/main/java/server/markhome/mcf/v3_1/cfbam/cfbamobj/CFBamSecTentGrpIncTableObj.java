// Description: Java 25 Table Object implementation for SecTentGrpInc.

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

public class CFBamSecTentGrpIncTableObj
	implements ICFBamSecTentGrpIncTableObj
{
	protected ICFBamSchemaObj schema;
	private Map<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj> members;
	private Map<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj> allSecTentGrpInc;
	private Map< ICFSecSecTentGrpIncByTentGrpIdxKey,
		Map<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj > > indexByTentGrpIdx;
	private Map< ICFSecSecTentGrpIncByNameIdxKey,
		Map<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj > > indexByNameIdx;
	public static String TABLE_NAME = "SecTentGrpInc";
	public static String TABLE_DBNAME = "sectentgrpinc";

	public CFBamSecTentGrpIncTableObj() {
		schema = null;
		members = new HashMap<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj>();
		allSecTentGrpInc = null;
		indexByTentGrpIdx = null;
		indexByNameIdx = null;
	}

	public CFBamSecTentGrpIncTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj>();
		allSecTentGrpInc = null;
		indexByTentGrpIdx = null;
		indexByNameIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecTentGrpIncTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( CFSecSecTentGrpIncTableObj.getBackingClassCode() );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( CFSecSecTentGrpIncTableObj.getRuntimeClassCode() );
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
		allSecTentGrpInc = null;
		indexByTentGrpIdx = null;
		indexByNameIdx = null;
		List<ICFSecSecTentGrpIncObj> toForget = new LinkedList<ICFSecSecTentGrpIncObj>();
		ICFSecSecTentGrpIncObj cur = null;
		Iterator<ICFSecSecTentGrpIncObj> iter = members.values().iterator();
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
	 *	CFBamSecTentGrpIncObj.
	 */
	@Override
	public ICFSecSecTentGrpIncObj newInstance() {
		ICFSecSecTentGrpIncObj inst = new CFBamSecTentGrpIncObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSecTentGrpIncObj.
	 */
	@Override
	public ICFSecSecTentGrpIncEditObj newEditInstance( ICFSecSecTentGrpIncObj orig ) {
		ICFSecSecTentGrpIncEditObj edit = new CFBamSecTentGrpIncEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecTentGrpIncObj realiseSecTentGrpInc( ICFSecSecTentGrpIncObj Obj ) {
		ICFSecSecTentGrpIncObj obj = Obj;
		ICFSecSecTentGrpIncPKey pkey = obj.getPKey();
		ICFSecSecTentGrpIncObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecTentGrpIncObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByTentGrpIdx != null ) {
				ICFSecSecTentGrpIncByTentGrpIdxKey keyTentGrpIdx =
					schema.getCFSecBackingStore().getFactorySecTentGrpInc().newByTentGrpIdxKey();
				keyTentGrpIdx.setRequiredSecTentGrpId( keepObj.getRequiredSecTentGrpId() );
				Map<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj > mapTentGrpIdx = indexByTentGrpIdx.get( keyTentGrpIdx );
				if( mapTentGrpIdx != null ) {
					mapTentGrpIdx.remove( keepObj.getPKey() );
					if( mapTentGrpIdx.size() <= 0 ) {
						indexByTentGrpIdx.remove( keyTentGrpIdx );
					}
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecTentGrpIncByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getFactorySecTentGrpInc().newByNameIdxKey();
				keyNameIdx.setRequiredInclName( keepObj.getRequiredInclName() );
				Map<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.remove( keepObj.getPKey() );
					if( mapNameIdx.size() <= 0 ) {
						indexByNameIdx.remove( keyNameIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTentGrpIdx != null ) {
				ICFSecSecTentGrpIncByTentGrpIdxKey keyTentGrpIdx =
					schema.getCFSecBackingStore().getFactorySecTentGrpInc().newByTentGrpIdxKey();
				keyTentGrpIdx.setRequiredSecTentGrpId( keepObj.getRequiredSecTentGrpId() );
				Map<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj > mapTentGrpIdx = indexByTentGrpIdx.get( keyTentGrpIdx );
				if( mapTentGrpIdx != null ) {
					mapTentGrpIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecTentGrpIncByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getFactorySecTentGrpInc().newByNameIdxKey();
				keyNameIdx.setRequiredInclName( keepObj.getRequiredInclName() );
				Map<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSecTentGrpInc != null ) {
				allSecTentGrpInc.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecTentGrpInc != null ) {
				allSecTentGrpInc.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTentGrpIdx != null ) {
				ICFSecSecTentGrpIncByTentGrpIdxKey keyTentGrpIdx =
					schema.getCFSecBackingStore().getFactorySecTentGrpInc().newByTentGrpIdxKey();
				keyTentGrpIdx.setRequiredSecTentGrpId( keepObj.getRequiredSecTentGrpId() );
				Map<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj > mapTentGrpIdx = indexByTentGrpIdx.get( keyTentGrpIdx );
				if( mapTentGrpIdx != null ) {
					mapTentGrpIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecTentGrpIncByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getFactorySecTentGrpInc().newByNameIdxKey();
				keyNameIdx.setRequiredInclName( keepObj.getRequiredInclName() );
				Map<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecTentGrpIncObj createSecTentGrpInc( ICFSecSecTentGrpIncObj Obj ) {
		ICFSecSecTentGrpIncObj obj = Obj;
		ICFSecSecTentGrpInc rec = obj.getSecTentGrpIncRec();
		schema.getCFSecBackingStore().getTableSecTentGrpInc().createSecTentGrpInc(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecTentGrpIncObj readSecTentGrpInc( ICFSecSecTentGrpIncPKey pkey ) {
		return( readSecTentGrpInc( pkey, false ) );
	}

	@Override
	public ICFSecSecTentGrpIncObj readSecTentGrpInc( ICFSecSecTentGrpIncPKey pkey, boolean forceRead ) {
		ICFSecSecTentGrpIncObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecTentGrpInc readRec = schema.getCFSecBackingStore().getTableSecTentGrpInc().readDerivedByIdIdx( null,
						pkey.getRequiredSecTentGrpId(),
						pkey.getRequiredInclName() );
			if( readRec != null ) {
				obj = schema.getSecTentGrpIncTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecTentGrpIncObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecTentGrpIncObj readSecTentGrpInc( CFLibDbKeyHash256 SecTentGrpId,
		String InclName ) {
		return( readSecTentGrpInc( SecTentGrpId,
			InclName, false ) );
	}

	@Override
	public ICFSecSecTentGrpIncObj readSecTentGrpInc( CFLibDbKeyHash256 SecTentGrpId,
		String InclName, boolean forceRead ) {
		ICFSecSecTentGrpIncObj obj = null;
		ICFSecSecTentGrpInc readRec = schema.getCFSecBackingStore().getTableSecTentGrpInc().readDerivedByIdIdx( null,
			SecTentGrpId,
			InclName );
		if( readRec != null ) {
				obj = schema.getSecTentGrpIncTableObj().newInstance();
			obj.setPKey( readRec.getPKey() );
			obj.setRec( readRec );
			obj = (ICFSecSecTentGrpIncObj)obj.realise();
		}
		return( obj );
	}

	@Override
	public ICFSecSecTentGrpIncObj readCachedSecTentGrpInc( ICFSecSecTentGrpIncPKey pkey ) {
		ICFSecSecTentGrpIncObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecTentGrpInc( ICFSecSecTentGrpIncObj obj )
	{
		final String S_ProcName = "CFBamSecTentGrpIncTableObj.reallyDeepDisposeSecTentGrpInc() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		ICFSecSecTentGrpIncPKey pkey = obj.getPKey();
		ICFSecSecTentGrpIncObj existing = readCachedSecTentGrpInc( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecTentGrpIncByTentGrpIdxKey keyTentGrpIdx = schema.getCFSecBackingStore().getFactorySecTentGrpInc().newByTentGrpIdxKey();
		keyTentGrpIdx.setRequiredSecTentGrpId( existing.getRequiredSecTentGrpId() );

		ICFSecSecTentGrpIncByNameIdxKey keyNameIdx = schema.getCFSecBackingStore().getFactorySecTentGrpInc().newByNameIdxKey();
		keyNameIdx.setRequiredInclName( existing.getRequiredInclName() );



		if( indexByTentGrpIdx != null ) {
			if( indexByTentGrpIdx.containsKey( keyTentGrpIdx ) ) {
				indexByTentGrpIdx.get( keyTentGrpIdx ).remove( pkey );
				if( indexByTentGrpIdx.get( keyTentGrpIdx ).size() <= 0 ) {
					indexByTentGrpIdx.remove( keyTentGrpIdx );
				}
			}
		}

		if( indexByNameIdx != null ) {
			if( indexByNameIdx.containsKey( keyNameIdx ) ) {
				indexByNameIdx.get( keyNameIdx ).remove( pkey );
				if( indexByNameIdx.get( keyNameIdx ).size() <= 0 ) {
					indexByNameIdx.remove( keyNameIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeSecTentGrpInc( ICFSecSecTentGrpIncPKey pkey ) {
		ICFSecSecTentGrpIncObj obj = readCachedSecTentGrpInc( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecTentGrpIncObj lockSecTentGrpInc( ICFSecSecTentGrpIncPKey pkey ) {
		ICFSecSecTentGrpIncObj locked = null;
		ICFSecSecTentGrpInc lockRec = schema.getCFSecBackingStore().getTableSecTentGrpInc().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecTentGrpIncTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecTentGrpIncObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecTentGrpInc", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecTentGrpIncObj> readAllSecTentGrpInc() {
		return( readAllSecTentGrpInc( false ) );
	}

	@Override
	public List<ICFSecSecTentGrpIncObj> readAllSecTentGrpInc( boolean forceRead ) {
		final String S_ProcName = "readAllSecTentGrpInc";
		if( ( allSecTentGrpInc == null ) || forceRead ) {
			Map<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj> map = new HashMap<ICFSecSecTentGrpIncPKey,ICFSecSecTentGrpIncObj>();
			allSecTentGrpInc = map;
			ICFSecSecTentGrpInc[] recList = schema.getCFSecBackingStore().getTableSecTentGrpInc().readAllDerived( null );
			ICFSecSecTentGrpInc rec;
			ICFSecSecTentGrpIncObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecTentGrpIncObj realised = (ICFSecSecTentGrpIncObj)obj.realise();
			}
		}
		int len = allSecTentGrpInc.size();
		ICFSecSecTentGrpIncObj arr[] = new ICFSecSecTentGrpIncObj[len];
		Iterator<ICFSecSecTentGrpIncObj> valIter = allSecTentGrpInc.values().iterator();
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
		ArrayList<ICFSecSecTentGrpIncObj> arrayList = new ArrayList<ICFSecSecTentGrpIncObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecTentGrpIncObj> cmp = new Comparator<ICFSecSecTentGrpIncObj>() {
			@Override
			public int compare( ICFSecSecTentGrpIncObj lhs, ICFSecSecTentGrpIncObj rhs ) {
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
					ICFSecSecTentGrpIncPKey lhsPKey = lhs.getPKey();
					ICFSecSecTentGrpIncPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecTentGrpIncObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecTentGrpIncObj> readCachedAllSecTentGrpInc() {
		final String S_ProcName = "readCachedAllSecTentGrpInc";
		ArrayList<ICFSecSecTentGrpIncObj> arrayList = new ArrayList<ICFSecSecTentGrpIncObj>();
		if( allSecTentGrpInc != null ) {
			int len = allSecTentGrpInc.size();
			ICFSecSecTentGrpIncObj arr[] = new ICFSecSecTentGrpIncObj[len];
			Iterator<ICFSecSecTentGrpIncObj> valIter = allSecTentGrpInc.values().iterator();
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
		Comparator<ICFSecSecTentGrpIncObj> cmp = new Comparator<ICFSecSecTentGrpIncObj>() {
			public int compare( ICFSecSecTentGrpIncObj lhs, ICFSecSecTentGrpIncObj rhs ) {
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
					ICFSecSecTentGrpIncPKey lhsPKey = lhs.getPKey();
					ICFSecSecTentGrpIncPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	/**
	 *	Return a sorted map of a page of the SecTentGrpInc-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecTentGrpIncObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	@Override
	public List<ICFSecSecTentGrpIncObj> pageAllSecTentGrpInc(CFLibDbKeyHash256 priorSecTentGrpId,
		String priorInclName )
	{
		final String S_ProcName = "pageAllSecTentGrpInc";
		Map<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj> map = new HashMap<ICFSecSecTentGrpIncPKey,ICFSecSecTentGrpIncObj>();
		ICFSecSecTentGrpInc[] recList = schema.getCFSecBackingStore().getTableSecTentGrpInc().pageAllRec( null,
			priorSecTentGrpId,
			priorInclName );
		ICFSecSecTentGrpInc rec;
		ICFSecSecTentGrpIncObj obj;
		ICFSecSecTentGrpIncObj realised;
		ArrayList<ICFSecSecTentGrpIncObj> arrayList = new ArrayList<ICFSecSecTentGrpIncObj>( recList.length );
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			realised = (ICFSecSecTentGrpIncObj)obj.realise();
			arrayList.add( realised );
		}
		return( arrayList );
	}

	@Override
	public ICFSecSecTentGrpIncObj readSecTentGrpIncByIdIdx( CFLibDbKeyHash256 SecTentGrpId,
		String InclName )
	{
		return( readSecTentGrpIncByIdIdx( SecTentGrpId,
			InclName,
			false ) );
	}

	@Override
	public ICFSecSecTentGrpIncObj readSecTentGrpIncByIdIdx( CFLibDbKeyHash256 SecTentGrpId,
		String InclName, boolean forceRead )
	{
		ICFSecSecTentGrpIncPKey pkey = schema.getCFSecBackingStore().getFactorySecTentGrpInc().newPKey();
		pkey.setRequiredContainerGroup(SecTentGrpId);
		pkey.setRequiredParentSubGroup(InclName);
		ICFSecSecTentGrpIncObj obj = readSecTentGrpInc( pkey, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecSecTentGrpIncObj> readSecTentGrpIncByTentGrpIdx( CFLibDbKeyHash256 SecTentGrpId )
	{
		return( readSecTentGrpIncByTentGrpIdx( SecTentGrpId,
			false ) );
	}

	@Override
	public List<ICFSecSecTentGrpIncObj> readSecTentGrpIncByTentGrpIdx( CFLibDbKeyHash256 SecTentGrpId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecTentGrpIncByTentGrpIdx";
		ICFSecSecTentGrpIncByTentGrpIdxKey key = schema.getCFSecBackingStore().getFactorySecTentGrpInc().newByTentGrpIdxKey();
		key.setRequiredSecTentGrpId( SecTentGrpId );
		Map<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj> dict;
		if( indexByTentGrpIdx == null ) {
			indexByTentGrpIdx = new HashMap< ICFSecSecTentGrpIncByTentGrpIdxKey,
				Map< ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj > >();
		}
		if( ( ! forceRead ) && indexByTentGrpIdx.containsKey( key ) ) {
			dict = indexByTentGrpIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj>();
			ICFSecSecTentGrpIncObj obj;
			ICFSecSecTentGrpInc[] recList = schema.getCFSecBackingStore().getTableSecTentGrpInc().readDerivedByTentGrpIdx( null,
				SecTentGrpId );
			ICFSecSecTentGrpInc rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecTentGrpIncTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecTentGrpIncObj realised = (ICFSecSecTentGrpIncObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTentGrpIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecTentGrpIncObj arr[] = new ICFSecSecTentGrpIncObj[len];
		Iterator<ICFSecSecTentGrpIncObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecTentGrpIncObj> arrayList = new ArrayList<ICFSecSecTentGrpIncObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecTentGrpIncObj> cmp = new Comparator<ICFSecSecTentGrpIncObj>() {
			@Override
			public int compare( ICFSecSecTentGrpIncObj lhs, ICFSecSecTentGrpIncObj rhs ) {
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
					ICFSecSecTentGrpIncPKey lhsPKey = lhs.getPKey();
					ICFSecSecTentGrpIncPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecTentGrpIncObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecTentGrpIncObj> readSecTentGrpIncByNameIdx( String InclName )
	{
		return( readSecTentGrpIncByNameIdx( InclName,
			false ) );
	}

	@Override
	public List<ICFSecSecTentGrpIncObj> readSecTentGrpIncByNameIdx( String InclName,
		boolean forceRead )
	{
		final String S_ProcName = "readSecTentGrpIncByNameIdx";
		ICFSecSecTentGrpIncByNameIdxKey key = schema.getCFSecBackingStore().getFactorySecTentGrpInc().newByNameIdxKey();
		key.setRequiredInclName( InclName );
		Map<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj> dict;
		if( indexByNameIdx == null ) {
			indexByNameIdx = new HashMap< ICFSecSecTentGrpIncByNameIdxKey,
				Map< ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj > >();
		}
		if( ( ! forceRead ) && indexByNameIdx.containsKey( key ) ) {
			dict = indexByNameIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj>();
			ICFSecSecTentGrpIncObj obj;
			ICFSecSecTentGrpInc[] recList = schema.getCFSecBackingStore().getTableSecTentGrpInc().readDerivedByNameIdx( null,
				InclName );
			ICFSecSecTentGrpInc rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecTentGrpIncTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecTentGrpIncObj realised = (ICFSecSecTentGrpIncObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNameIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecTentGrpIncObj arr[] = new ICFSecSecTentGrpIncObj[len];
		Iterator<ICFSecSecTentGrpIncObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecTentGrpIncObj> arrayList = new ArrayList<ICFSecSecTentGrpIncObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecTentGrpIncObj> cmp = new Comparator<ICFSecSecTentGrpIncObj>() {
			@Override
			public int compare( ICFSecSecTentGrpIncObj lhs, ICFSecSecTentGrpIncObj rhs ) {
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
					ICFSecSecTentGrpIncPKey lhsPKey = lhs.getPKey();
					ICFSecSecTentGrpIncPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecTentGrpIncObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecTentGrpIncObj readCachedSecTentGrpIncByIdIdx( CFLibDbKeyHash256 SecTentGrpId,
		String InclName )
	{
		ICFSecSecTentGrpIncObj obj = null;
		ICFSecSecTentGrpIncPKey pkey = schema.getCFSecBackingStore().getFactorySecTentGrpInc().newPKey();
		pkey.setRequiredContainerGroup(SecTentGrpId);
		pkey.setRequiredParentSubGroup(InclName);
		pkey.setRequiredContainerGroup(SecTentGrpId);
		pkey.setRequiredParentSubGroup(InclName);
		obj = readCachedSecTentGrpInc( pkey );
		return( obj );
	}

	@Override
	public List<ICFSecSecTentGrpIncObj> readCachedSecTentGrpIncByTentGrpIdx( CFLibDbKeyHash256 SecTentGrpId )
	{
		final String S_ProcName = "readCachedSecTentGrpIncByTentGrpIdx";
		ICFSecSecTentGrpIncByTentGrpIdxKey key = schema.getCFSecBackingStore().getFactorySecTentGrpInc().newByTentGrpIdxKey();
		key.setRequiredSecTentGrpId( SecTentGrpId );
		ArrayList<ICFSecSecTentGrpIncObj> arrayList = new ArrayList<ICFSecSecTentGrpIncObj>();
		if( indexByTentGrpIdx != null ) {
			Map<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj> dict;
			if( indexByTentGrpIdx.containsKey( key ) ) {
				dict = indexByTentGrpIdx.get( key );
				int len = dict.size();
				ICFSecSecTentGrpIncObj arr[] = new ICFSecSecTentGrpIncObj[len];
				Iterator<ICFSecSecTentGrpIncObj> valIter = dict.values().iterator();
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
			ICFSecSecTentGrpIncObj obj;
			Iterator<ICFSecSecTentGrpIncObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecTentGrpIncObj> cmp = new Comparator<ICFSecSecTentGrpIncObj>() {
			@Override
			public int compare( ICFSecSecTentGrpIncObj lhs, ICFSecSecTentGrpIncObj rhs ) {
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
					ICFSecSecTentGrpIncPKey lhsPKey = lhs.getPKey();
					ICFSecSecTentGrpIncPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFSecSecTentGrpIncObj> readCachedSecTentGrpIncByNameIdx( String InclName )
	{
		final String S_ProcName = "readCachedSecTentGrpIncByNameIdx";
		ICFSecSecTentGrpIncByNameIdxKey key = schema.getCFSecBackingStore().getFactorySecTentGrpInc().newByNameIdxKey();
		key.setRequiredInclName( InclName );
		ArrayList<ICFSecSecTentGrpIncObj> arrayList = new ArrayList<ICFSecSecTentGrpIncObj>();
		if( indexByNameIdx != null ) {
			Map<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj> dict;
			if( indexByNameIdx.containsKey( key ) ) {
				dict = indexByNameIdx.get( key );
				int len = dict.size();
				ICFSecSecTentGrpIncObj arr[] = new ICFSecSecTentGrpIncObj[len];
				Iterator<ICFSecSecTentGrpIncObj> valIter = dict.values().iterator();
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
			ICFSecSecTentGrpIncObj obj;
			Iterator<ICFSecSecTentGrpIncObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecTentGrpIncObj> cmp = new Comparator<ICFSecSecTentGrpIncObj>() {
			@Override
			public int compare( ICFSecSecTentGrpIncObj lhs, ICFSecSecTentGrpIncObj rhs ) {
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
					ICFSecSecTentGrpIncPKey lhsPKey = lhs.getPKey();
					ICFSecSecTentGrpIncPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeSecTentGrpIncByIdIdx( CFLibDbKeyHash256 SecTentGrpId,
		String InclName )
	{
		ICFSecSecTentGrpIncObj obj = readCachedSecTentGrpIncByIdIdx( SecTentGrpId,
				InclName );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecTentGrpIncByTentGrpIdx( CFLibDbKeyHash256 SecTentGrpId )
	{
		final String S_ProcName = "deepDisposeSecTentGrpIncByTentGrpIdx";
		ICFSecSecTentGrpIncObj obj;
		List<ICFSecSecTentGrpIncObj> arrayList = readCachedSecTentGrpIncByTentGrpIdx( SecTentGrpId );
		if( arrayList != null )  {
			Iterator<ICFSecSecTentGrpIncObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecTentGrpIncByNameIdx( String InclName )
	{
		final String S_ProcName = "deepDisposeSecTentGrpIncByNameIdx";
		ICFSecSecTentGrpIncObj obj;
		List<ICFSecSecTentGrpIncObj> arrayList = readCachedSecTentGrpIncByNameIdx( InclName );
		if( arrayList != null )  {
			Iterator<ICFSecSecTentGrpIncObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	/**
	 *	Read a page of data as a List of SecTentGrpInc-derived instances sorted by their primary keys,
	 *	as identified by the duplicate TentGrpIdx key attributes.
	 *
	 *	@param	SecTentGrpId	The SecTentGrpInc key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecTentGrpInc-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecTentGrpIncObj> pageSecTentGrpIncByTentGrpIdx( CFLibDbKeyHash256 SecTentGrpId,
		CFLibDbKeyHash256 priorSecTentGrpId,
		String priorInclName )
	{
		final String S_ProcName = "pageSecTentGrpIncByTentGrpIdx";
		ICFSecSecTentGrpIncByTentGrpIdxKey key = schema.getCFSecBackingStore().getFactorySecTentGrpInc().newByTentGrpIdxKey();
		key.setRequiredSecTentGrpId( SecTentGrpId );
		List<ICFSecSecTentGrpIncObj> retList = new LinkedList<ICFSecSecTentGrpIncObj>();
		ICFSecSecTentGrpIncObj obj;
		ICFSecSecTentGrpInc[] recList = schema.getCFSecBackingStore().getTableSecTentGrpInc().pageRecByTentGrpIdx( null,
				SecTentGrpId,
			priorSecTentGrpId,
			priorInclName );
		ICFSecSecTentGrpInc rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecTentGrpIncTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecTentGrpIncObj realised = (ICFSecSecTentGrpIncObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	/**
	 *	Read a page of data as a List of SecTentGrpInc-derived instances sorted by their primary keys,
	 *	as identified by the duplicate NameIdx key attributes.
	 *
	 *	@param	InclName	The SecTentGrpInc key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecTentGrpInc-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecTentGrpIncObj> pageSecTentGrpIncByNameIdx( String InclName,
		CFLibDbKeyHash256 priorSecTentGrpId,
		String priorInclName )
	{
		final String S_ProcName = "pageSecTentGrpIncByNameIdx";
		ICFSecSecTentGrpIncByNameIdxKey key = schema.getCFSecBackingStore().getFactorySecTentGrpInc().newByNameIdxKey();
		key.setRequiredInclName( InclName );
		List<ICFSecSecTentGrpIncObj> retList = new LinkedList<ICFSecSecTentGrpIncObj>();
		ICFSecSecTentGrpIncObj obj;
		ICFSecSecTentGrpInc[] recList = schema.getCFSecBackingStore().getTableSecTentGrpInc().pageRecByNameIdx( null,
				InclName,
			priorSecTentGrpId,
			priorInclName );
		ICFSecSecTentGrpInc rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecTentGrpIncTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecTentGrpIncObj realised = (ICFSecSecTentGrpIncObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	@Override
	public ICFSecSecTentGrpIncObj updateSecTentGrpInc( ICFSecSecTentGrpIncObj Obj ) {
		ICFSecSecTentGrpIncObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecTentGrpInc().updateSecTentGrpInc( null,
			Obj.getSecTentGrpIncRec() );
		obj = (ICFSecSecTentGrpIncObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecTentGrpInc( ICFSecSecTentGrpIncObj Obj ) {
		ICFSecSecTentGrpIncObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecTentGrpInc().deleteSecTentGrpInc( null,
			obj.getSecTentGrpIncRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecTentGrpIncByIdIdx( CFLibDbKeyHash256 SecTentGrpId,
		String InclName )
	{
		ICFSecSecTentGrpIncObj obj = readSecTentGrpInc(SecTentGrpId,
				InclName);
		if( obj != null ) {
			ICFSecSecTentGrpIncEditObj editObj = (ICFSecSecTentGrpIncEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecTentGrpIncEditObj)obj.beginEdit();
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
		deepDisposeSecTentGrpIncByIdIdx( SecTentGrpId,
				InclName );
	}

	@Override
	public void deleteSecTentGrpIncByTentGrpIdx( CFLibDbKeyHash256 SecTentGrpId )
	{
		ICFSecSecTentGrpIncByTentGrpIdxKey key = schema.getCFSecBackingStore().getFactorySecTentGrpInc().newByTentGrpIdxKey();
		key.setRequiredSecTentGrpId( SecTentGrpId );
		if( indexByTentGrpIdx == null ) {
			indexByTentGrpIdx = new HashMap< ICFSecSecTentGrpIncByTentGrpIdxKey,
				Map< ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj > >();
		}
		if( indexByTentGrpIdx.containsKey( key ) ) {
			Map<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj> dict = indexByTentGrpIdx.get( key );
			schema.getCFSecBackingStore().getTableSecTentGrpInc().deleteSecTentGrpIncByTentGrpIdx( null,
				SecTentGrpId );
			Iterator<ICFSecSecTentGrpIncObj> iter = dict.values().iterator();
			ICFSecSecTentGrpIncObj obj;
			List<ICFSecSecTentGrpIncObj> toForget = new LinkedList<ICFSecSecTentGrpIncObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTentGrpIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecTentGrpInc().deleteSecTentGrpIncByTentGrpIdx( null,
				SecTentGrpId );
		}
		deepDisposeSecTentGrpIncByTentGrpIdx( SecTentGrpId );
	}

	@Override
	public void deleteSecTentGrpIncByNameIdx( String InclName )
	{
		ICFSecSecTentGrpIncByNameIdxKey key = schema.getCFSecBackingStore().getFactorySecTentGrpInc().newByNameIdxKey();
		key.setRequiredInclName( InclName );
		if( indexByNameIdx == null ) {
			indexByNameIdx = new HashMap< ICFSecSecTentGrpIncByNameIdxKey,
				Map< ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj > >();
		}
		if( indexByNameIdx.containsKey( key ) ) {
			Map<ICFSecSecTentGrpIncPKey, ICFSecSecTentGrpIncObj> dict = indexByNameIdx.get( key );
			schema.getCFSecBackingStore().getTableSecTentGrpInc().deleteSecTentGrpIncByNameIdx( null,
				InclName );
			Iterator<ICFSecSecTentGrpIncObj> iter = dict.values().iterator();
			ICFSecSecTentGrpIncObj obj;
			List<ICFSecSecTentGrpIncObj> toForget = new LinkedList<ICFSecSecTentGrpIncObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByNameIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecTentGrpInc().deleteSecTentGrpIncByNameIdx( null,
				InclName );
		}
		deepDisposeSecTentGrpIncByNameIdx( InclName );
	}
}